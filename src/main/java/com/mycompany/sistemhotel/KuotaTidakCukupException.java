package com.mycompany.sistemhotel;

public class KuotaTidakCukupException extends HotelException {
    private static final long serialVersionUID = 1L;

    public KuotaTidakCukupException(String kodeKamar, int diminta, int sisa) {
        super("Kuota " + kodeKamar + " tidak cukup (diminta " + diminta + ", sisa " + sisa + ")");
    }
}