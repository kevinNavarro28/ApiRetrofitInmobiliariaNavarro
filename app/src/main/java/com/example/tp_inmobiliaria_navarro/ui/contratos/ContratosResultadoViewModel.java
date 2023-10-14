package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class ContratosResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contratoM;

    private Context context;

    private ApiClient api = ApiClient.getApi();


    public ContratosResultadoViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData<Contrato> getcontratoM(){
        if(contratoM==null){
            contratoM = new MutableLiveData<>();

        }
        return contratoM;
    }

    public  void obtenerContrato(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
       Contrato contrato = api.obtenerContratoVigente(inmueble);
        contratoM.setValue(contrato);
    }

}