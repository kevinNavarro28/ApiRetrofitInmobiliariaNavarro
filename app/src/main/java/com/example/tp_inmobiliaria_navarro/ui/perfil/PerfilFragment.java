package com.example.tp_inmobiliaria_navarro.ui.perfil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.example.tp_inmobiliaria_navarro.databinding.FragmentPerfilBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;

    private PerfilViewModel mv;

    private Propietario pro=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel galleryViewModel =
               mv = new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        mv.getpropietarioM().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                pro=propietario;
                Log.d("salida fragment", String.valueOf(propietario.getId()));
                binding.EtCodigoPerfil.setText(String.valueOf(propietario.getId()));
                binding.EtDniPerfil.setText(String.valueOf(propietario.getDni()));
                binding.EtNombrePerfil.setText(propietario.getNombre());
                binding.EtApellidoPerfil.setText(propietario.getApellido());
                binding.EtMailPerfil.setText(propietario.getEmail());
                binding.EtClavePerfil.setText(propietario.getContraseña());
                binding.EtTelefono.setText(propietario.getTelefono());
                binding.ImgFoto.setImageResource(propietario.getAvatar());


            }
        });

        binding.btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.Actualizar();
                mv.ActuPropietario(
                        binding.EtCodigoPerfil.getText().toString(),
                        binding.EtDniPerfil.getText().toString(),
                        binding.EtNombrePerfil.getText().toString(),
                        binding.EtApellidoPerfil.getText().toString(),
                        binding.EtMailPerfil.getText().toString(),
                        binding.EtClavePerfil.getText().toString(),
                        binding.EtTelefono.getText().toString(),
                        pro.getAvatar()


                );

            }
        });
        mv.getEditable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                binding.EtDniPerfil.setEnabled(aBoolean);
                binding.EtNombrePerfil.setEnabled(aBoolean);
                binding.EtApellidoPerfil.setEnabled(aBoolean);
                binding.EtMailPerfil.setEnabled(aBoolean);
                binding.EtClavePerfil.setEnabled(aBoolean);
                binding.EtTelefono.setEnabled(aBoolean);

                binding.btEditar.setText(aBoolean ? "Guardar" : "Editar");
            }
        });





        mv.LeerPropietario();




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}