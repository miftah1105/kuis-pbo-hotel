package com.mycompany.sistemhotel;

public class FormatTransaksiException extends HotelException {
    private static final long serialVersionUID = 1L;

    public FormatTransaksiException(String pesan) {
        super(pesan);
    }
}