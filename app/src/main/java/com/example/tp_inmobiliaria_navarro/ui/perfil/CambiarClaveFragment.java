package com.example.tp_inmobiliaria_navarro.ui.perfil;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentCambiarClaveBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentPerfilBinding;

public class CambiarClaveFragment extends Fragment {

    private CambiarClaveViewModel mv;

    private FragmentCambiarClaveBinding binding;


    public static CambiarClaveFragment newInstance() {
        return new CambiarClaveFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CambiarClaveViewModel galleryViewModel =
                mv = new ViewModelProvider(this).get(CambiarClaveViewModel.class);
        binding = FragmentCambiarClaveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.BtCambiarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.cambiarClave(binding.EtNuevaClave.getText().toString());

            }
        });

        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

}