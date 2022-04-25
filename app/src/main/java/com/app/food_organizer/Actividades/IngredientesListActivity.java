package com.app.food_organizer.Actividades;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class IngredientesListActivity extends SingleFragmentActivity {

    public static final String EXTRA_INGREDIENTES_ID =
            "com/app/food_organizer/Actividades/IngredientesListActivity.java";
    public static final String EXTRA_INGREDIENTES2_ID =
            "com/app/food_organizer/Actividades/IngredientesListActivity.java2";

    public static Intent newIntent(Context packageContext, UUID menuId, UUID platilloId) {
        Intent intent = new Intent(packageContext, IngredientesListActivity.class);
        intent.putExtra(EXTRA_INGREDIENTES_ID, menuId);
        intent.putExtra(EXTRA_INGREDIENTES2_ID, platilloId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID menuId = (UUID) getIntent().getSerializableExtra(EXTRA_INGREDIENTES_ID);
        UUID platilloId = (UUID) getIntent().getSerializableExtra(EXTRA_INGREDIENTES2_ID);

        return new IngredientesListFragment().newInstance(menuId, platilloId);
    }
}