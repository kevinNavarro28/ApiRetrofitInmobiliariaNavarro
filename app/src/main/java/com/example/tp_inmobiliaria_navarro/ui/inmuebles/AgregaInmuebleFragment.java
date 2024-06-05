package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentAgregaInmuebleBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInmueblesBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;

public class AgregaInmuebleFragment extends Fragment {

    private AgregaInmuebleViewModel mv;

    private FragmentAgregaInmuebleBinding binding;

    private Spinner spinerTipo;
    private Spinner spinerUso;
    private Uri imageUri;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE_REQUEST = 1;
    private String tipoSelec;
    private String usoSelec;

    private Inmueble inmu ;

    public static AgregaInmuebleFragment newInstance() {
        return new AgregaInmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AgregaInmuebleViewModel slideshowViewModel =
                mv = new ViewModelProvider(this).get(AgregaInmuebleViewModel.class);

        binding = FragmentAgregaInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.BtAgregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                abrirGaleria();
            }
        });




        spinerTipo = binding.spinnerTipoInmueble;
        spinerUso = binding.spinnerUsoInmueble;

        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(getContext(),
                R.array.Tipo, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerTipo.setAdapter(adapterTipo);

        binding.spinnerTipoInmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoSelec = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapterUso = ArrayAdapter.createFromResource(getContext(),
                R.array.Uso, android.R.layout.simple_spinner_item);
        adapterUso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerUso.setAdapter(adapterUso);
        binding.spinnerUsoInmueble.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                usoSelec = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.BtAgregarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String direccion = binding.EtAgregarDireccion.getText().toString();
                String ambientes = (binding.EtAgregarAmbiente.getText().toString()+"");
                String superficie = (binding.EtAgregarSuperficie.getText().toString()+"");
                String precio = (binding.EtAgregarPrecio.getText().toString()+"");
                boolean disponible = false;
                String tipo = tipoSelec;
                String uso = usoSelec;
                    Inmueble inmu = new Inmueble();
                    inmu.setDisponible(disponible);
                    inmu.setTipo(tipo);
                    inmu.setUso(uso);

                    mv.crearInmuebleRetro(inmu,direccion, ambientes,superficie,precio, imageUri);

            }

        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                abrirGaleria();
            } else {
                Toast.makeText(getContext(), "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                binding.ImgAgregarInmueble.setImageURI(imageUri);
            }
        }



    }
}


