package com.example.tp_inmobiliaria_navarro.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentContratosResultadoBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentDetallePagosBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Pago;

import java.util.ArrayList;

public class DetallePagos extends Fragment {

    private DetallePagosViewModel mViewModel;
    private FragmentDetallePagosBinding binding;

    public static DetallePagos newInstance() {
        return new DetallePagos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetallePagosViewModel.class);
        binding =  FragmentDetallePagosBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Bundle bundle = getArguments();

        mViewModel.getpagosM().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pago>>() {
            @Override
            public void onChanged(ArrayList<Pago> pagos) {
                RecyclerView rv = binding.RvPagos;

                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);

                DetallePagosAdapter adapter = new DetallePagosAdapter(getContext(), pagos, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
        mViewModel.obtenerPago();






        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallePagosViewModel.class);
        // TODO: Use the ViewModel
    }

}