package com.example.tp_inmobiliaria_navarro.ui.perfil;

import android.app.Application;
import android.content.Context;
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

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private  MutableLiveData<Propietario> propietarioM;
    private MutableLiveData<Boolean> Editable = new MutableLiveData<>(false);





    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();

    }
    public LiveData<Propietario> getpropietarioM(){
        if(propietarioM == null){
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }



    public  void LeerPropietario(){
        String token = ApiClientRetrofit.leerToken(getApplication());
        if(token!= null){
            ApiClientRetrofit.MisEndpoint api = ApiClientRetrofit.getEndPoint();
            Call<Propietario> call = api.miPerfil(token);
            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()){
                        propietarioM.postValue(response.body());
                    }else{
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

    public LiveData<Boolean> getEditable() {
        return Editable;
    }



    public void Actualizar(Propietario propietario) {
            if(!Editable.getValue()){
                Editable.setValue(true);
            }else {
            Editable.setValue(false);
            String token = ApiClientRetrofit.leerToken(getApplication());
            if (token != null) {
                ApiClientRetrofit.MisEndpoint api = ApiClientRetrofit.getEndPoint();
                Call<Propietario> call = api.modificarUsuario(token, propietario);
                call.enqueue(new Callback<Propietario>() {
                    @Override
                    public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                        if (response.isSuccessful()) {
                            propietarioM.postValue(response.body());
                            Toast.makeText(getApplication(), "Perfil Actualizado", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("salida", "Incorrecto");
                            Toast.makeText(getApplication(), "No Actualizo ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Propietario> call, Throwable throwable) {
                        Log.d("salida", "Falla:" + throwable.getMessage());
                    }
                });

            }
            }
        }
    }








