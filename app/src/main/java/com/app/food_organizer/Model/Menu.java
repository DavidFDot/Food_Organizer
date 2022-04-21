package com.app.food_organizer.Model;

import java.util.ArrayList;

public class Menu {
    private int id;
    private String nombre;
    private ArrayList<Platillos> mPlatillos;

    public Menu(String nombre) {
        this.nombre = nombre;
        mPlatillos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
}
