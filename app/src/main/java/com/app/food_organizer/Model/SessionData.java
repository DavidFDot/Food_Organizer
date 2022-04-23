package com.app.food_organizer.Model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SessionData {

    private static SessionData sSessionData;

    private List<Menu> mMenuList;

    private SessionData(Context context) {
        mMenuList = new ArrayList<>();
        for (int i = 1; i < 30; i++){
            mMenuList.add(new Menu("Menu #" + i));
        }
        for (int i = 1; i < 20; i++){
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

    public Menu getMenu(int id) {
        for (Menu menu : mMenuList) {
            if (menu.getId().equals(id)) {
                return menu;
            }
        }
        return null;
    }

}
