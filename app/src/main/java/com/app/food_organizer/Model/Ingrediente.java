package com.app.food_organizer.Model;

import java.util.UUID;

public class Ingrediente {
    private UUID mUUID;
    private String mNombre;
    private String cantidad;
    private Medidas mMedidas;

    public UUID getId() {
        return mUUID;
    }

    public Ingrediente(String ingrediente) {
        mUUID = UUID.randomUUID();
        mNombre = ingrediente;
        cantidad = String.valueOf(1);
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public int getCantidad() {
        return Integer.parseInt(cantidad);
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Medidas getMedidas() {
        return mMedidas;
    }

    public void setMedidas(Medidas medidas) {
        mMedidas = medidas;
    }
}

