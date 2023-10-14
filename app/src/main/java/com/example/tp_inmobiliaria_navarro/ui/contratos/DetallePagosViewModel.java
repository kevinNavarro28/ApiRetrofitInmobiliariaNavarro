package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Pago;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

import java.util.ArrayList;

public class DetallePagosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Pago>> pagosM;

    private ArrayList<Pago> pagos ;

    private Context context;

    private ApiClient api = ApiClient.getApi();


    public DetallePagosViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData<ArrayList<Pago>> getpagosM(){
        if(pagosM==null){
            pagosM = new MutableLiveData<ArrayList<Pago>>();

        }
        return pagosM;
    }

    public  void obtenerPago(Bundle bundle){
        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        pagos = new ArrayList<Pago>();
        pagos =  api.obtenerPagos(contrato);
        pagosM.setValue(pagos);
    }

}