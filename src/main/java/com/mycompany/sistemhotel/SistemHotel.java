package com.mycompany.sistemhotel;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemHotel implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String FILE_SERIALIZATION = "sistem_state.ser"; // digit ketiga = 7
    private static final String FILE_LAPORAN = "laporan.txt";

    private final String namaHotel;
    private final HashMap<String, Tamu> daftarTamu = new HashMap<>();
    private final HashMap<String, Kamar> daftarKamar = new HashMap<>();
    private final ArrayList<Transaksi> logTransaksi = new ArrayList<>();

    public SistemHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public void tambahTamu(String id, String nama) {
        daftarTamu.put(id, new Tamu(id, nama));
    }

    public void tambahKamar(String kode, String nama, int kuota) {
        String n = nama.toLowerCase();
        Kamar kamar;
        if (n.contains("suite")) {
            kamar = new SuiteRoom(kode, nama, kuota);
        } else if (n.contains("reguler")) {
            kamar = new RegulerRoom(kode, nama, kuota);
        } else {
            kamar = new StandardRoom(kode, nama, kuota);
        }
        daftarKamar.put(kode, kamar);
    }

    public void jalankanTransaksiDariFile(String namaFile) throws IOException {
        int nomorBaris = 0;
        int berhasil = 0;
        int gagal = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(namaFile))) {
            String baris;
            while ((baris = br.readLine()) != null) {
                nomorBaris++;
                if (baris.trim().isEmpty()) {
                    continue;
                }
                try {
                    prosesBaris(baris);
                    berhasil++;
                } catch (HotelException e) {
                    gagal++;
                    System.out.println("[GAGAL] Baris " + nomorBaris + ": " + e.getMessage());
                }
            }
        }
        System.out.println("Selesai membaca " + nomorBaris + " baris: "
                + berhasil + " berhasil, " + gagal + " gagal.");
    }

    private void prosesBaris(String baris) throws HotelException {
        String data = baris.trim();
        if (!data.endsWith(".")) {
            throw new FormatTransaksiException("baris tidak diakhiri titik: \"" + data + "\"");
        }
        data = data.substring(0, data.length() - 1);

        String[] bagian = data.split("\\.");
        if (bagian.length != 3) {
            throw new FormatTransaksiException("jumlah field tidak sesuai: \"" + baris.trim() + "\"");
        }

        String idTamu = ambilNilai(bagian[0], "ID");
        String kodeKamar = ambilNilai(bagian[1], "KD");
        String teksJumlah = ambilNilai(bagian[2], "JML");

        int jumlah;
        try {
            jumlah = Integer.parseInt(teksJumlah);
        } catch (NumberFormatException e) {
            throw new FormatTransaksiException("JML bukan angka: \"" + teksJumlah + "\"");
        }
        if (jumlah <= 0) {
            throw new FormatTransaksiException("JML harus lebih dari 0");
        }

        Tamu tamu = daftarTamu.get(idTamu);
        if (tamu == null) {
            throw new TamuTidakDitemukanException(idTamu);
        }
        Kamar kamar = daftarKamar.get(kodeKamar);
        if (kamar == null) {
            throw new KamarTidakDitemukanException(kodeKamar);
        }
        if (!kamar.cukup(jumlah)) {
            throw new KuotaTidakCukupException(kodeKamar, jumlah, kamar.getSisaKuota());
        }

        kamar.kurangiKuota(jumlah);
        tamu.tambahPesanan(jumlah);
        logTransaksi.add(new Transaksi(tamu, kamar, jumlah));
        System.out.println("[OK] " + tamu.getNama() + " memesan " + jumlah + " x "
                + kamar.getNama() + " (sisa " + kamar.getSisaKuota() + ")");
    }

    private String ambilNilai(String potongan, String kunciHarapan) throws FormatTransaksiException {
        String[] kv = potongan.split(">", 2);
        if (kv.length != 2 || !kv[0].equals(kunciHarapan) || kv[1].isEmpty()) {
            throw new FormatTransaksiException("field " + kunciHarapan + " tidak valid: \"" + potongan + "\"");
        }
        return kv[1];
    }

    public void simpanLaporanAkhir() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SERIALIZATION))) {
            oos.writeObject(this);
            System.out.println("[INFO] State sistem diserialisasi ke " + FILE_SERIALIZATION);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal serialisasi: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_LAPORAN))) {
            pw.println("=== DAFTAR AKTOR AKTIF - " + namaHotel + " ===");
            for (Tamu t : daftarTamu.values()) {
                pw.println(t.getId() + " - " + t.getNama());
            }
            System.out.println("[INFO] Laporan ditulis ke " + FILE_LAPORAN);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menulis laporan: " + e.getMessage());
        }
    }
}