package com.app.food_organizer.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Menu {
    private UUID mId;
    private String nombre;
    private ArrayList<Platillos> mPlatillos;

    public Menu(String nombre) {
        this.mId = UUID.randomUUID();
        this.nombre = nombre;
        mPlatillos = new ArrayList<>();
    }

    public UUID getId() {
        return mId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
