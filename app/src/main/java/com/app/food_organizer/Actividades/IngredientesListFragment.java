package com.app.food_organizer.Actividades;

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

import com.app.food_organizer.Model.Ingrediente;
import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.Platillo;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;
import java.util.UUID;

public class IngredientesListFragment extends Fragment {

    private static final String ARG_MENU_ID = "menu_id";
    private static final String ARG_PLATILLO_ID = "platillo_id";

    private Platillo mPlatillo;//este platillo es el que tiene los ingredientes
    private Menu mMenu;
    private RecyclerView mIngredientesRecycler;
    private IngredientesAdapter mIngredientesAdapter;
    private TextView mMenuBarText;
    private Button mCrearButton;


    public static IngredientesListFragment newInstance(UUID menuId, UUID platilloId) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_MENU_ID, menuId);
        arguments.putSerializable(ARG_PLATILLO_ID, platilloId);

        IngredientesListFragment fragment = new IngredientesListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID menuId = (UUID) getArguments().getSerializable(ARG_MENU_ID);
        mMenu = SessionData.get(getActivity()).getMenu(menuId);
        UUID platilloId = (UUID) getArguments().getSerializable(ARG_PLATILLO_ID);
        mPlatillo = SessionData.get(getActivity()).getPlatillo(menuId, platilloId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menus_platillos_list, container, false);

        mMenuBarText = view.findViewById(R.id.menu_bar_text);
        mMenuBarText.setText("INGREDIENTES de " + mPlatillo.getNombre());

        mCrearButton = view.findViewById(R.id.menu_bar_crear_button);
        mCrearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionData.get(getActivity()).getMenu(mMenu.getId()).getPlatillo(mPlatillo.getId())
                        .getIngredientes().add(new Ingrediente("Ingrediente"));
                updateUi();
            }
        });

        mIngredientesRecycler = view.findViewById(R.id.menu_recyclerView);
        mIngredientesRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return view;
    }

    private void updateUi() {
        List<Ingrediente> mIngredientes = mPlatillo.getIngredientes();

        mIngredientesAdapter = new IngredientesAdapter(mIngredientes);
        mIngredientesRecycler.setAdapter(mIngredientesAdapter);
    }

    private class IngredienteHolder extends RecyclerView.ViewHolder {
        private EditText mTitleTextView;
        private EditText mNumeroUnidades;
        private Button mBorrarButton;
        private Ingrediente mIngrediente;

        public IngredienteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_ingrediente, parent, false));
            mTitleTextView = itemView.findViewById(R.id.ingrediente_nombre);
            mTitleTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mIngrediente.setNombre(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            mNumeroUnidades = itemView.findViewById(R.id.cantidad);
            mNumeroUnidades.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mIngrediente.setCantidad(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            mBorrarButton = (Button) itemView.findViewById(R.id.borrar_ingrediente);
            mBorrarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SessionData.get(getActivity()).deleteIngrediente(mMenu.getId()
                            , mPlatillo.getId(), mIngrediente.getId());
                    updateUi();
                }
            });
        }

        public void bind(Ingrediente ingrediente) {
            mIngrediente = ingrediente;
            mTitleTextView.setText(mIngrediente.getNombre());
            mNumeroUnidades.setText(String.valueOf(mIngrediente.getCantidad()));
        }
    }

    private class IngredientesAdapter extends RecyclerView.Adapter<IngredienteHolder> {
        private List<Ingrediente> mIngredientes;

        public IngredientesAdapter(List<Ingrediente> ingredientes) {
            mIngredientes = ingredientes;
        }

        @NonNull
        @Override
        public IngredienteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new IngredienteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull IngredienteHolder holder, int position) {
            Ingrediente ingrediente = mIngredientes.get(position);
            holder.bind(ingrediente);
        }

        @Override
        public int getItemCount() {
            return mIngredientes.size();
        }
    }
}
