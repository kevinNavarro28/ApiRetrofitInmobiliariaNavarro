package com.example.tp_inmobiliaria_navarro.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;

public class InquilinosResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquiM;

    private Context context;




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
        Inquilino inquilino = (Inquilino) bundle.getSerializable("inquilino");
        inquiM.postValue(inquilino);

    }

}