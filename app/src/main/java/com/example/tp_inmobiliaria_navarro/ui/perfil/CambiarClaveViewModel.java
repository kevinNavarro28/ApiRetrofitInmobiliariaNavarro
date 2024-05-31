package com.example.tp_inmobiliaria_navarro.ui.perfil;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.modelo.CambiarClaveRequest;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarClaveViewModel extends AndroidViewModel {



    public CambiarClaveViewModel(@NonNull Application application) {
        super(application);
    }

    public void cambiarClave(String nuevaClave) {
        ApiClientRetrofit.MisEndpoint mep = ApiClientRetrofit.getEndPoint();
        String token = ApiClientRetrofit.leerToken(getApplication());
        CambiarClaveRequest request = new CambiarClaveRequest(nuevaClave);
        Log.d("request",request.toString());
        Call<Void> call = mep.cambiarClave(token, request);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Cambio de contraseña exitoso
                    Log.d("Retrofit", "Cambio de clave exitoso");
                    Toast.makeText(getApplication(),"Se Cambio la Contraseña exitosamente",Toast.LENGTH_LONG).show();
                } else {
                    // Error en el cambio de contraseña
                    Log.e("Retrofit", "Error al cambiar la clave: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error en la comunicación con el servidor
                Log.e("Retrofit", "Error al cambiar la clave: " + t.getMessage());
            }
        });
    }


    }



