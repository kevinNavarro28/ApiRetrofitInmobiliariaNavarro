package com.example.tp_inmobiliaria_navarro.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInmueblesResultadoBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInquilinosBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInquilinosResultadoBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;
import com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmueblesResultadoViewModel;

public class InquilinosResultado extends Fragment {

    private InquilinosResultadoViewModel mViewModel;

    private FragmentInquilinosResultadoBinding binding;

    public static InquilinosResultado newInstance() {
        return new InquilinosResultado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinosResultadoViewModel.class);
        binding = FragmentInquilinosResultadoBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Bundle bundle = getArguments();

        mViewModel.getinquiM().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {

                binding.EtNombreInquilino.setText(inquilino.getNombre());
                binding.EtApellidoInquilino.setText(inquilino.getApellido());
                binding.EtDniInquilino.setText(inquilino.getDni()+"");
                binding.EtEmailInquilino.setText(inquilino.getEmail());
                binding.EtTelefonoInquilino.setText(inquilino.getTelefono());
                binding.EtGaranteInquilino.setText(inquilino.getNombreGarante());
                binding.EtTelefonoGarante.setText(inquilino.getTelefonoGarante());


            }
        });

        mViewModel.obtenerInquilino(bundle);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InquilinosResultadoViewModel.class);
        // TODO: Use the ViewModel
    }

}