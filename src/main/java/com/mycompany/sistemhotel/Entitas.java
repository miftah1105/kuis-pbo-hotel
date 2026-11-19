package com.mycompany.sistemhotel;

import java.io.Serializable;

public abstract class Entitas implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private final String nama;

    protected Entitas(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public abstract String deskripsi();

    @Override
    public String toString() {
        return deskripsi();
    }
}