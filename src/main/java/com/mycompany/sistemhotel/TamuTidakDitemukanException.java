package com.mycompany.sistemhotel;

public class TamuTidakDitemukanException extends HotelException {
    private static final long serialVersionUID = 1L;

    public TamuTidakDitemukanException(String idTamu) {
        super("Tamu dengan ID " + idTamu + " tidak terdaftar");
    }
}