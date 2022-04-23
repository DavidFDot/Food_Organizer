package com.app.food_organizer.Actividades;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class MenuPlatillos extends SingleFragmentActivity {

    public static final String EXTRA_PLATILLO_ID =
            "com/app/food_organizer/Actividades/MenuPlatillos.java";

    public static Intent newIntent(Context packageContext, UUID menuId) {
        Intent intent = new Intent(packageContext, MenuPlatillos.class);
        intent.putExtra(EXTRA_PLATILLO_ID, menuId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new PlatillosListFragment();
    }
}