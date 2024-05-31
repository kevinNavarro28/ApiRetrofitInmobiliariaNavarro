package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInmueblesResultadoBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

public class InmueblesResultadoFragment extends Fragment {

    private InmueblesResultadoViewModel mViewModel;

    private FragmentInmueblesResultadoBinding binding;
    private Inmueble inmuebleactual=null;
    public static InmueblesResultadoFragment newInstance() {
        return new InmueblesResultadoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InmueblesResultadoViewModel.class);
        binding = FragmentInmueblesResultadoBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Bundle bundle = getArguments();
        mViewModel.getunmum().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.EtSuperficie.setText(inmueble.getSuperficie()+"");
                binding.EtAmbientes.setText(inmueble.getAmbientes()+"");
                binding.EtDireccion.setText(inmueble.getDireccion());
                binding.EtPrecio.setText(String.valueOf(inmueble.getPrecio()));
                binding.EtTipo.setText(inmueble.getTipo());
                binding.EtUso.setText(inmueble.getUso());
                binding.CbDisponible.setChecked(inmueble.getDisponible());
                Glide.with(InmueblesResultadoFragment.this).load(ApiClientRetrofit.URL+inmueble.getImagenUrl()).into(binding.ImgInmueble);
                Log.d("img",inmueble.getImagenUrl());
                inmuebleactual=inmueble;

            }
        });

        binding.CbDisponible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mViewModel.cambiarDisponibilidad(inmuebleactual);
            }
        });

        mViewModel.obtenerInmueble(bundle);



        return root;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(InmueblesResultadoViewModel.class);
        // TODO: Use the ViewModel
    }

}