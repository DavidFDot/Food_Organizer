package com.app.food_organizer.Actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;

public class MenuListFragment extends Fragment {
    private RecyclerView mMenuRecycler;
    private  MenuAdapter mMenuAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_menu_list,container,false);
        mMenuRecycler=view.findViewById(R.id.menu_recyclerView);
        mMenuRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return view;
    }

    private void updateUi() {
        SessionData sessionData = SessionData.get(getActivity());
        List<com.app.food_organizer.Model.Menu> menu = sessionData.getMenus();
        mMenuAdapter = new MenuAdapter(menu);
        mMenuRecycler.setAdapter(mMenuAdapter);
    }

    private class MenuHolder extends RecyclerView.ViewHolder {
        public MenuHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_menu, parent, false));
        }
    }
    private class MenuAdapter extends RecyclerView.Adapter<MenuHolder> {
        private List<com.app.food_organizer.Model.Menu> mMenus;
        public MenuAdapter(List<Menu> menus) {
            mMenus = menus;
        }

        @NonNull
        @Override
        public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new MenuHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuHolder holder, int position) {

        }



        @Override
        public int getItemCount() {
            return mMenus.size();
        }
    }
}