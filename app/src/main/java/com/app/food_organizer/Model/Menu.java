package com.app.food_organizer.Model;

import java.util.ArrayList;

public class Menu {
    private  int id;
    public  static  int counter=1;
    private String nombre;
    private ArrayList<Platillos> mPlatillos;

    public Menu(String nombre) {
        this.id=counter++;
        this.nombre = nombre;
        mPlatillos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return  this.nombre;
    }
}
