package com.app.food_organizer.Actividades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.food_organizer.Model.Platillos;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;
import java.util.UUID;

public class IngredientesListFragment extends Fragment {


    private static final String ARG_PLATILLO_ID = "platillo_id";

    private Platillos mPlatillo;
    private RecyclerView mMenuRecycler;
    private IngredientesAdapter mIngredientesAdapter;
    private TextView mMenuBarText;
    private Button mCrearButton;
    private Object mIngredientes;

    public static PlatillosListFragment newInstance(UUID menuId) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_PLATILLO_ID, menuId);

        PlatillosListFragment fragment = new PlatillosListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID menuId = (UUID) getArguments().getSerializable(ARG_PLATILLO_ID);
        //mPlatillo = SessionData.get(getActivity()).getMenu(menuId).getPlatillo();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus_platillos_list, container, false);

        mMenuBarText = view.findViewById(R.id.menu_bar_text);
        mMenuBarText.setText("INGREDIENTES");

        mCrearButton = view.findViewById(R.id.menu_bar_crear_button);
        mCrearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mMenuRecycler = view.findViewById(R.id.menu_recyclerView);
        mMenuRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return view;
    }

    private void updateUi() {
        SessionData sessionData = SessionData.get(getActivity());

        //List<Platillos> mPlatillos = mMenu.getPlatillos();

        mIngredientesAdapter = new IngredientesAdapter((List<Platillos>) mIngredientes);
        mMenuRecycler.setAdapter(mIngredientesAdapter);
    }

    private class IngredienteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Platillos mPlatillo;


        public IngredienteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_platillo, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.platillo_title);

        }

        public void bind(Platillos platillos) {
            mPlatillo = platillos;
            mTitleTextView.setText(mPlatillo.getNombre());
        }

        @Override
        public void onClick(View view) {

        }
    }

    private class IngredientesAdapter extends RecyclerView.Adapter<IngredienteHolder> {
        private List<Platillos> mPlatillos;

        public IngredientesAdapter(List<Platillos> platillos) {
            mPlatillos = platillos;
        }

        @NonNull
        @Override
        public IngredienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new IngredienteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull IngredienteHolder holder, int position) {
            Platillos platillos = mPlatillos.get(position);
            holder.bind(platillos);
        }


        @Override
        public int getItemCount() {
            return mPlatillos.size();
        }
    }
}
