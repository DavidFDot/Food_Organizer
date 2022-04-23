package com.app.food_organizer.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;

public class MenuListFragment extends Fragment {

    private TextView mMenuBarText;
    private Button mCrearButton;
    private RecyclerView mMenuRecycler;
    private MenuAdapter mMenuAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);

        mMenuBarText = view.findViewById(R.id.menu_bar_text);

        mCrearButton = view.findViewById(R.id.menu_bar_crear_button);
        mCrearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mMenuRecycler = view.findViewById(R.id.menu_recyclerView);
        mMenuRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        updateUi();
        return view;
    }

    private void updateUi() {
        SessionData sessionData = SessionData.get(getActivity());

        List<Menu> menu = sessionData.getMenus();

        mMenuAdapter = new MenuAdapter(menu);
        mMenuRecycler.setAdapter(mMenuAdapter);
    }

    private class MenuHolder extends RecyclerView.ViewHolder {
        private TextView mMenuName;
        private Button mEnterButton;
        private Button mDeleteButton;
        private Menu mMenu;

        public MenuHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_menu, parent, false));

            mMenuName = itemView.findViewById(R.id.menu_title);

            mEnterButton = itemView.findViewById(R.id.enter_button);
            mEnterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = PlatillosListActivity.newIntent(getActivity(), mMenu.getId());
                    startActivity(intent);
                }
            });

            mDeleteButton = itemView.findViewById(R.id.delete_menu_button);
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        public void bind(Menu menu) {
            mMenu = menu;
            mMenuName.setText(mMenu.getNombre());
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
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MenuHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
            Menu menu = mMenus.get(position);
            holder.bind(menu);
        }


        @Override
        public int getItemCount() {
            return mMenus.size();
        }
    }
}
