package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesResultadoViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> inmuM;

    private Context context;




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


    public void cambiarDisponibilidad(Inmueble inmueble){
        int in = inmueble.getIdInmueble();
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        String token = ApiClientRetrofit.leerToken(getApplication().getApplicationContext());
        Call<Inmueble> cambioDisponibilidad = ApiClientRetrofit.getEndPoint().inmuebleCambiarDisponibilidad(in,inmueble,token);
        cambioDisponibilidad.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context,"Cambio de Disponibilidad",Toast.LENGTH_LONG).show();
                    Inmueble updatedInmueble = response.body();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Toast.makeText(context,"No se Cambio Correctamente",Toast.LENGTH_LONG).show();

            }
        });


    }

    public void obtenerInmueble(Bundle bundle){
        Inmueble bun = (Inmueble) bundle.getSerializable("inmueble");
        inmuM.postValue(bun);

    }


}