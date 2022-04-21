package com.app.food_organizer.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Platillos {
    private UUID mId;
    private String mNombre;
    private ArrayList<Ingredientes> mIngredientes;

    public Platillos(String nombre) {
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

    public ArrayList<Ingredientes> getIngredientes() {
        return mIngredientes;
    }

    public void setIngredientes(ArrayList<Ingredientes> ingredientes) {
        mIngredientes = ingredientes;
    }
}
