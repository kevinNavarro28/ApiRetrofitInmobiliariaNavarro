package com.example.tp_inmobiliaria_navarro;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivityViewModel extends AndroidViewModel {

    private Context context ;

    private MutableLiveData<Propietario> proM;



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
        String token = ApiClientRetrofit.leerToken(getApplication());
        if(token!= null){
            ApiClientRetrofit.MisEndpoint api = ApiClientRetrofit.getEndPoint();
            Call<Propietario> call = api.miPerfil(token);
            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()) {
                        // Obtener el Propietario actualmente observado
                        Propietario propietarioActual = proM.getValue();
                        // Obtener el Propietario actualizado desde la respuesta
                        Propietario propietarioActualizado = response.body();

                        if (propietarioActual != null && propietarioActualizado != null) {
                            // Actualizar los campos individuales del propietario actualizado
                            propietarioActual.setNombre(propietarioActualizado.getNombre());
                            propietarioActual.setApellido(propietarioActualizado.getApellido());
                            propietarioActual.setEmail(propietarioActualizado.getEmail());

                            // Notificar el cambio al objeto LiveData
                            proM.setValue(propietarioActual);}
                            else {
                                // Si alguno de los propietarios es nulo, simplemente publica el nuevo valor
                                proM.postValue(propietarioActualizado);}}
                    else{
                        Log.d("salida","Incorrecto");
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable throwable) {
                    Log.d("salida","Falla:"+throwable.getMessage());
                }
            });

        }

    }

    }

