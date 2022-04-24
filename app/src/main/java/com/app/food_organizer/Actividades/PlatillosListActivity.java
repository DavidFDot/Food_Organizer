package com.app.food_organizer.Actividades;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class PlatillosListActivity extends SingleFragmentActivity {

    public static final String EXTRA_PLATILLOS_ID =
            "com/app/food_organizer/Actividades/PlatillosListActivity.java";

    public static Intent newIntent(Context packageContext, UUID menuId) {
        Intent intent = new Intent(packageContext, PlatillosListActivity.class);
        intent.putExtra(EXTRA_PLATILLOS_ID, menuId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        UUID menuId = (UUID) getIntent().getSerializableExtra(EXTRA_PLATILLOS_ID);

        return new PlatillosListFragment().newInstance(menuId);
    }
}