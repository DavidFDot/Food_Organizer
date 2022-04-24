package com.app.food_organizer.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.app.food_organizer.Model.Ingredientes;
import com.app.food_organizer.R;

import java.util.UUID;

public class IngredientesListActivity extends SingleFragmentActivity {

    public static final String EXTRA_INGREDIENTES_ID =
            "com/app/food_organizer/Actividades/IngredientesListActivity.java";

    public static Intent newIntent(Context packageContext, UUID menuId) {
        Intent intent = new Intent(packageContext, PlatillosListActivity.class);
        intent.putExtra(EXTRA_INGREDIENTES_ID, menuId);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return null;
    }
}