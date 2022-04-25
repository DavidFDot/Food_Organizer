package com.app.food_organizer.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.Platillo;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;
import java.util.UUID;

public class PlatillosListFragment extends Fragment {

    private static final String ARG_MENU_ID = "menu_id_arg";

    private Menu mMenu;//aca esta el menu especifico con el ID
    private RecyclerView mPLatillosRecycler;
    private PlatilloAdapter mPlatilloAdapter;
    private TextView mMenuBarText;
    private Button mCrearButton;

    public static PlatillosListFragment newInstance(UUID menuId) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_MENU_ID, menuId);

        PlatillosListFragment fragment = new PlatillosListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID menuId = (UUID) getArguments().getSerializable(ARG_MENU_ID);
        mMenu = SessionData.get(getActivity()).getMenu(menuId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus_platillos_list, container, false);

        mMenuBarText = view.findViewById(R.id.menu_bar_text);
        mMenuBarText.setText("PLATILLOS de " + mMenu.getNombre());

        mCrearButton = view.findViewById(R.id.menu_bar_crear_button);
        mCrearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionData.get(getActivity()).getMenu(mMenu.getId()).getPlatillos().add(new Platillo("New Platillo"));
                updateUi();
            }
        });

        mPLatillosRecycler = view.findViewById(R.id.menu_recyclerView);
        mPLatillosRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return view;
    }

    private void updateUi() {
        List<Platillo> mPlatillos = mMenu.getPlatillos();

        mPlatilloAdapter = new PlatilloAdapter(mPlatillos);
        mPLatillosRecycler.setAdapter(mPlatilloAdapter);
    }

    private class PlatilloHolder extends RecyclerView.ViewHolder {
        private EditText mTitleTextView;
        private Button mVerButton;
        private Button mBorrarButton;
        private Platillo mPlatillo;

        public PlatilloHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_platillo, parent, false));
            mTitleTextView = itemView.findViewById(R.id.platillo_title);
            mTitleTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mPlatillo.setNombre(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            mVerButton = itemView.findViewById(R.id.ver_button);
            mVerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = IngredientesListActivity.newIntent(getActivity(), mMenu.getId(), mPlatillo.getId());
                    startActivity(intent);
                }
            });

            mBorrarButton = itemView.findViewById(R.id.delete_menu_button);
            mBorrarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SessionData.get(getActivity()).deletePlatilo(mMenu.getId(), mPlatillo.getId());
                    updateUi();
                }
            });

        }

        public void bind(Platillo platillo) {
            mPlatillo = platillo;
            mTitleTextView.setText(mPlatillo.getNombre());
        }

    }

    private class PlatilloAdapter extends RecyclerView.Adapter<PlatilloHolder> {
        private List<Platillo> mPlatillos;

        public PlatilloAdapter(List<Platillo> platillos) {
            mPlatillos = platillos;
        }

        @NonNull
        @Override
        public PlatilloHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PlatilloHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PlatilloHolder holder, int position) {
            Platillo platillo = mPlatillos.get(position);
            holder.bind(platillo);
        }


        @Override
        public int getItemCount() {
            return mPlatillos.size();
        }
    }
}
