package com.example.tp_inmobiliaria_navarro.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentContratosResultadoBinding;
import com.example.tp_inmobiliaria_navarro.databinding.FragmentInquilinosResultadoBinding;
import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;
import com.example.tp_inmobiliaria_navarro.ui.inquilinos.InquilinosResultadoViewModel;
import com.example.tp_inmobiliaria_navarro.ui.contratos.*;

public class ContratosResultado extends Fragment {

    private ContratosResultadoViewModel mViewModel;
    private FragmentContratosResultadoBinding binding;

    private Contrato contratoActual = null;

    private Inquilino inquilinoActual = null;

    public static ContratosResultado newInstance() {
        return new ContratosResultado();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratosResultadoViewModel.class);
        binding = FragmentContratosResultadoBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Bundle bundle = getArguments();

        mViewModel.getcontratoM().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                String formattedFechaInicio = Formateo_Date.formatDate(contrato.getFechaInicio());
                String formattedFechaFin = Formateo_Date.formatDate(contrato.getFechaFin());
                Log.d("contrato", String.valueOf(contrato.getprecio()));

                binding.EtFechaInicio.setText(formattedFechaInicio);
                binding.EtFechaFinalizacion.setText(formattedFechaFin);

               binding.EtMontoalquiler.setText(contrato.getprecio()+"");
               binding.EtInquilino.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());
               binding.EtInmueble.setText(contrato.getInmueble().getDireccion());

                contratoActual= contrato;
                inquilinoActual =contrato.getInquilino();

            }
        });

        mViewModel.obtenerContrato(bundle);

        binding.BtPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("contrato",contratoActual);
                Navigation.findNavController(v).navigate(R.id.action_contratosResultado_to_detallePagos,bundle1);

            }
        });

        binding.BtDetalleInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("inquilino",inquilinoActual);
                Log.d("inquilino2",inquilinoActual.getNombre());
                Navigation.findNavController(v).navigate(R.id.action_contratosResultado_to_inquilinosResultado,bundle2);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ContratosResultadoViewModel.class);
        // TODO: Use the ViewModel
    }

}