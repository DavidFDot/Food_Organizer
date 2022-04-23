package com.app.food_organizer.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.food_organizer.Model.Menu;
import com.app.food_organizer.Model.Platillos;
import com.app.food_organizer.Model.SessionData;
import com.app.food_organizer.R;

import java.util.List;

public class PlatillosListFragment extends Fragment {


    private RecyclerView mMenuRecycler;
    private PlatilloAdapter mPlatilloAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        mMenuRecycler = view.findViewById(R.id.menu_recyclerView);
        mMenuRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        updateUi();
        return view;
    }

    private void updateUi() {
        SessionData sessionData = SessionData.get(getActivity());

        List<Platillos> platillos = sessionData.getMenu().getPla;

        mPlatilloAdapter = new PlatilloAdapter(platillos);
        mMenuRecycler.setAdapter(mPlatilloAdapter);
    }

    private class PlatilloHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private Platillos mPlatillo;


        public PlatilloHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_menu, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.menu_title);

        }

        public void bind(Platillos platillos) {
            mPlatillo = platillos;
            mTitleTextView.setText(mPlatillo.getNombre());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), MenuPlatillos.class);
            startActivity(intent);
        }
    }

    private class PlatilloAdapter extends RecyclerView.Adapter<PlatilloHolder> {
        private List<Platillos> mPlatillos;

        public PlatilloAdapter(List<Platillos> platillos) {
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
            Platillos platillos = mPlatillos.get(position);
            holder.bind(platillos);
        }


        @Override
        public int getItemCount() {
            return mPlatillos.size();
        }
    }
}
