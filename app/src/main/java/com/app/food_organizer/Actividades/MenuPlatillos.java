package com.app.food_organizer.Actividades;

import androidx.fragment.app.Fragment;

public class MenuPlatillos extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new PlatillosListFragment();
    }
}