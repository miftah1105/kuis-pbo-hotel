package com.mycompany.sistemhotel;

public abstract class Kamar extends Entitas {
    private static final long serialVersionUID = 1L;

    private int sisaKuota;

    protected Kamar(String id, String nama, int kuota) {
        super(id, nama);
        this.sisaKuota = kuota;
    }

    public int getSisaKuota() {
        return sisaKuota;
    }

    public boolean cukup(int jumlah) {
        return jumlah <= sisaKuota;
    }

    public void kurangiKuota(int jumlah) {
        this.sisaKuota -= jumlah;
    }

    public abstract double getTarifPerMalam();

    public abstract String getTipe();

    public double hitungBiaya(int jumlah) {
        return getTarifPerMalam() * jumlah;
    }

    @Override
    public String deskripsi() {
        return getTipe() + "[" + getId() + " - " + getNama() + ", sisa " + sisaKuota + "]";
    }
}