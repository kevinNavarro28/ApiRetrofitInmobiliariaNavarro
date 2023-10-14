package com.example.tp_inmobiliaria_navarro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class MenuActivityViewModel extends AndroidViewModel {

    private Context context ;

    private MutableLiveData<Propietario> proM;

    private ApiClient apiClient = ApiClient.getApi();

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);
        context=application;



    }

    public LiveData<Propietario> getproM(){
        if(proM==null){
            proM = new MutableLiveData<>();
        }
        return proM;
    }

    public void obtenerpropietario(){
        Propietario propietario = apiClient.obtenerUsuarioActual();
        proM.setValue(propietario);
    }
}
