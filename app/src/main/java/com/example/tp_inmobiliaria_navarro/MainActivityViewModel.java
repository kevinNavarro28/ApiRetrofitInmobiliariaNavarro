package com.example.tp_inmobiliaria_navarro;

import static android.Manifest.permission.CALL_PHONE;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> propietarioM;



    private ApiClient apiClient = ApiClient.getApi();

    public MainActivityViewModel(@NonNull Application application) {

        super(application);
        this.context=application;
    }
    public LiveData<Propietario> getpropietarioM() {

        if (propietarioM == null) {

            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public void ingresar(String email,String pass){

       Propietario pro = apiClient.login(email,pass);
       if(pro!=null){
           propietarioM.setValue(pro);
       }
       else {
           Toast.makeText(context,"Usuario o Clave Incorrectos",Toast.LENGTH_LONG).show();
       }

    }

    }




