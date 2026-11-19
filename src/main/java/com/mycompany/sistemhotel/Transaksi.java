package com.mycompany.sistemhotel;

import java.io.Serializable;

public class Transaksi implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Tamu tamu;
    private final Kamar kamar;
    private final int jumlah;
    private final double totalBiaya;

    public Transaksi(Tamu tamu, Kamar kamar, int jumlah) {
        this.tamu = tamu;
        this.kamar = kamar;
        this.jumlah = jumlah;
        this.totalBiaya = kamar.hitungBiaya(jumlah);
    }

    public Tamu getTamu() {
        return tamu;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }
}