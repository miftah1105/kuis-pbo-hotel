package com.mycompany.sistemhotel;

public class StandardRoom extends Kamar {
    private static final long serialVersionUID = 1L;

    public StandardRoom(String id, String nama, int kuota) {
        super(id, nama, kuota);
    }

    @Override
    public double getTarifPerMalam() {
        return 300000;
    }

    @Override
    public String getTipe() {
        return "Standard";
    }
}