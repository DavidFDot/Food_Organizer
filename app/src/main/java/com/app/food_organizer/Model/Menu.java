package com.app.food_organizer.Model;

import java.util.ArrayList;
import java.util.UUID;

public class Menu {
    private UUID mId;
    private String nombre;
    private ArrayList<Platillo> mPlatillos;

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

    public ArrayList<Platillo> getPlatillos() {
        return mPlatillos;
    }

    public void setPlatillos(ArrayList<Platillo> platillos) {
        mPlatillos = platillos;
    }

    public Platillo getPlatillo(UUID id) {
        for (Platillo platillo : mPlatillos) {
            if (platillo.getId().equals(id)) {
                return platillo;
            }
        }
        return null;
    }
}
