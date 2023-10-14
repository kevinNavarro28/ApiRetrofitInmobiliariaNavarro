package com.example.tp_inmobiliaria_navarro.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class InquilinosResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquiM;

    private Context context;

    private ApiClient api = ApiClient.getApi();


    public InquilinosResultadoViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData<Inquilino> getinquiM(){
        if(inquiM==null){
            inquiM = new MutableLiveData<>();

        }
        return inquiM;
    }

    public  void obtenerInquilino(Bundle bundle){
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        Inquilino inquilino = api.obtenerInquilino(inmueble);
        inquiM.setValue(inquilino);
    }

}