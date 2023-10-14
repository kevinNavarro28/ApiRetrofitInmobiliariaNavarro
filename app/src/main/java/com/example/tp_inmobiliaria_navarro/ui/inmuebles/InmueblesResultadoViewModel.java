package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class InmueblesResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuM;

    private Context context;

    private ApiClient api = ApiClient.getApi();


    public InmueblesResultadoViewModel(@NonNull Application application) {
        super(application);

        context=application;

    }
    public LiveData<Inmueble> getunmum(){
        if(inmuM==null){
            inmuM = new MutableLiveData<>();

        }
        return inmuM;
    }


    public void ActuInmueble(Inmueble inmueble){
      api.actualizarInmueble(inmueble);
    }

    public void obtenerInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        inmuM.setValue(inmueble);
    }


}