package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp_inmobiliaria_navarro.databinding.FragmentInmueblesBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.ui.inicio.InicioViewModel;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {

    private FragmentInmueblesBinding   binding;
    private InmueblesViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmueblesViewModel slideshowViewModel =
            mv=    new ViewModelProvider(this).get(InmueblesViewModel.class);

        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                RecyclerView rv = binding.RvListaInmuebles;

                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);

                InmuebleAdapter adapter = new InmuebleAdapter(getContext(), inmuebles, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
        mv.obtenerInmueble();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}