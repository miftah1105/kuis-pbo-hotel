package com.mycompany.sistemhotel;

public class HotelException extends Exception {
    private static final long serialVersionUID = 1L;

    public HotelException(String pesan) {
        super(pesan);
    }
}