package com.mycompany.sistemhotel;

public class SuiteRoom extends Kamar {
    private static final long serialVersionUID = 1L;

    public SuiteRoom(String id, String nama, int kuota) {
        super(id, nama, kuota);
    }

    @Override
    public double getTarifPerMalam() {
        return 1500000;
    }

    @Override
    public String getTipe() {
        return "Suite";
    }
}