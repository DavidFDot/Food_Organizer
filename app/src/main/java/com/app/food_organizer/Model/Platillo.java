package com.app.food_organizer.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Platillo {
    private final UUID mId;
    private String mNombre;
    private ArrayList<Ingrediente> mIngredientes;

    public Platillo(String nombre) {
        mId = UUID.randomUUID();
        mNombre = nombre;
        mIngredientes = new ArrayList<>();
    }

    public UUID getId() {
        return mId;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return mIngredientes;
    }

}
