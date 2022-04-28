package com.app.food_organizer.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.app.food_organizer.Model.Ingrediente;
import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.Platillo;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.UUID;

public class ResumenActivity extends AppCompatActivity {

    private TextView resumenTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        resumenTextview = findViewById(R.id.resumen_view);

        UUID menuId = (UUID) getIntent().getSerializableExtra("id_del_menu");
        Menu menu = SessionData.get(getApplicationContext()).getMenu(menuId);
        resumenTextview.setTextSize(24);
        resumenTextview.append("- " + menu.getNombre()+"\n");
        for (Platillo platillo:menu.getPlatillos()) {
            resumenTextview.append("\t\t- "+platillo.getNombre()+"\n");
            for (Ingrediente ingrediente:platillo.getIngredientes()) {
                resumenTextview.append("\t\t\t\t- "+ingrediente.getNombre()+" "+ingrediente.getCantidad()+"\n");
            }
        }
    }
}