package com.app.food_organizer.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SessionData {

    private static SessionData sSessionData;

    private List<Menu> mMenuList;

    private SessionData(Context context) {
        mMenuList = new ArrayList<>();
        mMenuList.add(new Menu("New Menu"));
        mMenuList.get(0).getPlatillos()
                .add(new Platillo("New Platillo"));
        mMenuList.get(0).getPlatillos()
                .get(0).getIngredientes()
                .add(new Ingrediente("Ingrediente"));
    }

    public static SessionData get(Context context) {

        if (sSessionData == null) {
            sSessionData = new SessionData(context);
        }
        return sSessionData;
    }

    public List<Menu> getMenus() {
        return mMenuList;
    }

    public Menu getMenu(UUID id) {
        for (Menu menu : mMenuList) {
            if (menu.getId().equals(id)) {
                return menu;
            }
        }
        return null;
    }

    public void deleteMenu(UUID id) {
        for (int i = 0; i < mMenuList.size(); i++) {
            if (mMenuList.get(i).getId().equals(id)) {
                mMenuList.remove(i);
            }
        }
    }

    public Platillo getPlatillo(UUID menuId, UUID platilloId) {
        for (Menu menu : mMenuList) {
            if (menu.getId().equals(menuId)) {
                for (Platillo platillo : menu.getPlatillos()) {
                    if (platillo.getId().equals(platilloId)) {
                        return platillo;
                    }
                }
            }
        }
        return null;
    }

    public void deletePlatilo(UUID menuId, UUID platilloId) {
        for (Menu menu : mMenuList) {
            if (menu.getId().equals(menuId)) {
                for (int i = 0; i < menu.getPlatillos().size(); i++) {
                    if (menu.getPlatillos().get(i).getId().equals(platilloId)) {
                        menu.getPlatillos().remove(i);
                    }
                }
            }
        }
    }

    public void deleteIngrediente(UUID menuId, UUID platilloId, UUID ingredienteId) {
        for (int i = 0; i < mMenuList.size(); i++) {
            if (mMenuList.get(i).getId().equals(menuId)) {
                for (int j = 0; j < mMenuList.get(i).getPlatillos().size(); j++) {
                    if (mMenuList.get(i).getPlatillos().get(j).getId().equals(platilloId)) {
                        for (int k = 0; k < mMenuList.get(i).getPlatillos().get(j).getIngredientes().size(); k++) {
                            if (mMenuList.get(i).getPlatillos().get(j).getIngredientes().get(k).getId().equals(ingredienteId)){
                                mMenuList.get(i).getPlatillos().get(j).getIngredientes().remove(k);
                            }
                        }
                    }
                }
            }
        }
    }
}
