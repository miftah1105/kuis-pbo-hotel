package com.mycompany.sistemhotel;

public class KamarTidakDitemukanException extends HotelException {
    private static final long serialVersionUID = 1L;

    public KamarTidakDitemukanException(String kodeKamar) {
        super("Kamar dengan kode " + kodeKamar + " tidak terdaftar");
    }
}