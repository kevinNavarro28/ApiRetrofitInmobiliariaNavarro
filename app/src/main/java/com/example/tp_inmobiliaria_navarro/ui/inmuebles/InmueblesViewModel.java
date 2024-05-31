package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private ArrayList<Inmueble> inmuebles ;
    private MutableLiveData<ArrayList<Inmueble>> lista;




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
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        String token = ApiClientRetrofit.leerToken(getApplication().getApplicationContext());
        Call<List<Inmueble>> call = mep.obtenerInmuebles(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    lista.postValue((ArrayList<Inmueble>) response.body());
                }
                else{
                    Log.d("salida",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable) {
                Log.d("salida","falla"+throwable.getMessage());
            }
        });


    }

}