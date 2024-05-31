package com.example.tp_inmobiliaria_navarro.ui.contratos;

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

import com.example.tp_inmobiliaria_navarro.databinding.FragmentContratosBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.ui.inquilinos.InquilinosAdapter;

import java.util.ArrayList;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;

    private ContratosViewModel mv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContratosViewModel homeViewModel =
                mv = new ViewModelProvider(this).get(ContratosViewModel.class);

        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mv.getLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Contrato>>() {
            @Override
            public void onChanged(ArrayList<Contrato> contratoes) {
                RecyclerView rv = binding.RvListaContratos;

                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);

                ContratosAdapter adapter = new ContratosAdapter(getContext(), contratoes, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });




        mv.obtenerContrato();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}