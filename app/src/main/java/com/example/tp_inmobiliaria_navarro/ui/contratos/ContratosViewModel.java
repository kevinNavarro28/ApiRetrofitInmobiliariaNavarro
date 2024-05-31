package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private ArrayList<Contrato> contrato;
    private MutableLiveData<ArrayList<Contrato>> lista;


    public ContratosViewModel(@NonNull Application application) {
        super(application);


    }

    public LiveData<ArrayList<Contrato>> getLista() {
        if (this.lista == null) {
            this.lista = new MutableLiveData<>();
        }
        return this.lista;

    }

    public void obtenerContrato() {
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        String token = ApiClientRetrofit.leerToken(getApplication().getApplicationContext());
        Call <List<Contrato>> call = mep.traerContratos(token);
        call.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    lista.postValue((ArrayList<Contrato>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable throwable) {
                Toast.makeText(getApplication(), "Error al conseguir los contratos", Toast.LENGTH_SHORT).show();
                Log.d("falla",throwable.getMessage());
            }
        });

    }


}