package com.mycompany.sistemhotel;

public class RegulerRoom extends Kamar {
    private static final long serialVersionUID = 1L;

    public RegulerRoom(String id, String nama, int kuota) {
        super(id, nama, kuota);
    }

    @Override
    public double getTarifPerMalam() {
        return 500000;
    }

    @Override
    public String getTipe() {
        return "Reguler";
    }
}