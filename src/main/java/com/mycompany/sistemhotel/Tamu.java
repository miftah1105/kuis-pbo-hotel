package com.mycompany.sistemhotel;

public class Tamu extends Entitas {
    private static final long serialVersionUID = 1L;

    private int totalKamarDipesan;

    public Tamu(String id, String nama) {
        super(id, nama);
        this.totalKamarDipesan = 0;
    }

    public int getTotalKamarDipesan() {
        return totalKamarDipesan;
    }

    public void tambahPesanan(int jumlah) {
        this.totalKamarDipesan += jumlah;
    }

    @Override
    public String deskripsi() {
        return "Tamu[" + getId() + " - " + getNama() + "]";
    }
}