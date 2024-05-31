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
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetallePagosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Pago>> pagosM;

    private ArrayList<Pago> pagos ;

    private Context context;




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

    public  void obtenerPago(){
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        String token = ApiClientRetrofit.leerToken(getApplication().getApplicationContext());
        Call<List<Pago>> call = mep.traerPagos(token);
        call.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    pagosM.postValue((ArrayList<Pago>) response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable throwable) {

            }
        });

    }

}