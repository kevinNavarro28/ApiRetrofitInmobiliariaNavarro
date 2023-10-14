package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

import java.util.ArrayList;

public class InmueblesViewModel extends AndroidViewModel {
    private ArrayList<Inmueble> inmuebles ;
    private MutableLiveData<ArrayList<Inmueble>> lista;

    private ApiClient api = ApiClient.getApi();


    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Inmueble>> getLista() {
        if(this.lista == null){
            this.lista = new MutableLiveData<>();
        }
        return this.lista;
    }

    public void  obtenerInmueble(){
        inmuebles = new ArrayList<>();
        inmuebles = api.obtnerPropiedades();
        lista.setValue(inmuebles);
    }

}