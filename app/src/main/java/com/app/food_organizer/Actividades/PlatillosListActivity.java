package com.app.food_organizer.Actividades;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class PlatillosListActivity extends SingleFragmentActivity {

    public static final String EXTRA_MENU_ID =
            "com/app/food_organizer/Actividades/MenuPlatillos.java";

    public static Intent newIntent(Context packageContext, UUID menuId) {
        Intent intent = new Intent(packageContext, PlatillosListActivity.class);
        intent.putExtra(EXTRA_MENU_ID, menuId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID menuId = (UUID) getIntent().getSerializableExtra(EXTRA_MENU_ID);

        return new PlatillosListFragment().newInstance(menuId);
    }
}