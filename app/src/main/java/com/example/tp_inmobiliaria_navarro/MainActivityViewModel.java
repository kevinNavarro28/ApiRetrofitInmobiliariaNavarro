package com.example.tp_inmobiliaria_navarro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> propietarioM;





    public MainActivityViewModel(@NonNull Application application) {

        super(application);
        this.context=application.getApplicationContext();
    }
    public LiveData<Propietario> getpropietarioM() {

        if (propietarioM == null) {

            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }

    public void ingresar(String email,String pass){
        ApiClientRetrofit.MisEndpoint api = ApiClientRetrofit.getEndPoint();
        Call<String> call = api.login(email,pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
                    guardaToken("Bearer "+token);
                    Log.d("token inicio ",token);
                    Intent intent = new Intent(context,MenuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    }
                else {
                    Toast.makeText(context,"Usuario o Clave Incorrectos",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.d("salida","Falla");
            }
        });


    }
            public void guardaToken(String token){
            ApiClientRetrofit.guardarToken(token,getApplication());

            }



        public void resetPassword(String email){
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        Call<Void> call = mep.resetearPassword(email);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context,"Se envio un correo para restablecer su contraseña",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d("Fallo",throwable.getMessage());
            }
        });
        }


    }




