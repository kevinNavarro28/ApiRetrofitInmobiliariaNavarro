package com.example.tp_inmobiliaria_navarro.ui.inquilinos;

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


import com.example.tp_inmobiliaria_navarro.databinding.FragmentInquilinosBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmuebleAdapter;

import java.util.ArrayList;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;

    private InquilinosViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinosViewModel homeViewModel =
              mv =  new ViewModelProvider(this).get(InquilinosViewModel.class);

        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mv.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                RecyclerView rv = binding.RvListaInquilinos;

                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);

                InquilinosAdapter adapter = new InquilinosAdapter(getContext(), inmuebles, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
        mv.obtenerInmuebleAlquilados();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}