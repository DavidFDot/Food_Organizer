package com.app.food_organizer.Model;

import android.content.Context;

import java.util.List;

public class SessionData {

    private static SessionData sSessionData;

    private List<Menu> mMenuList;

    private SessionData(Context context) {
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

    public Menu getCrime(int id) {
        for (Menu menu : mMenuList) {
            if (menu.getId() == id) {
                return menu;
            }
        }
        return null;
    }

}
