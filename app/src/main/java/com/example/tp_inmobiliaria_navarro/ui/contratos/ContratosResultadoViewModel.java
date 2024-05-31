package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContratosResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contratoM;

    private Context context;




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
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        contratoM.postValue(contrato);



    }



}