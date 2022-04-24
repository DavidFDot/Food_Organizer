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
        for (int i = 1; i < 5; i++) {
            mMenuList.add(new Menu("Menu #" + i));
        }
        for (int i = 1; i < 20; i++) {
            mMenuList.get(1).getPlatillos().add(new Platillos("platillo #" + i));
        }
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
}
