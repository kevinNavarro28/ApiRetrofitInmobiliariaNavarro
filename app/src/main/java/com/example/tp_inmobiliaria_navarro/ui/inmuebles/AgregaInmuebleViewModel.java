package com.example.tp_inmobiliaria_navarro.ui.inmuebles;



import android.app.Application;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.RealPathUtil;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregaInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<String> validationMessage = new MutableLiveData<>();

    public AgregaInmuebleViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getValidationMessage() {
        return validationMessage;
    }
    public boolean validarCampos(String direccion, Integer ambientesStr,Integer superficieStr, Double precioStr, String tipo, String uso, Uri imageUri) {
        if (direccion.isEmpty() || (ambientesStr==null) || superficieStr==null || precioStr==null || tipo == null || uso == null) {
            validationMessage.setValue("Por favor, complete todos los campos");
            return false;
        }

        try {
            Integer.parseInt(String.valueOf(ambientesStr));
            Integer.parseInt(String.valueOf(superficieStr));
            Double.parseDouble(String.valueOf(precioStr));
        } catch (NumberFormatException e) {
            validationMessage.setValue("Por favor, ingrese valores numéricos válidos");
            return false;
        }

        return true;
    }


    public void crearInmuebleRetro(Inmueble inmueble, Uri imageUri){
        String token = ApiClientRetrofit.leerToken(getApplication());
        ApiClientRetrofit.MisEndpoint api = ApiClientRetrofit.getEndPoint();
        RequestBody Direccion = RequestBody.create(MediaType.parse("application/json"),inmueble.getDireccion());
        RequestBody Ambientes = RequestBody.create(MediaType.parse("application/json"),inmueble.getAmbientes()+"");
        RequestBody Superficie = RequestBody.create(MediaType.parse("application/json"),inmueble.getSuperficie()+"");
        RequestBody Tipo = RequestBody.create(MediaType.parse("application/json"),inmueble.getTipo());
        RequestBody Uso = RequestBody.create(MediaType.parse("application/json"),inmueble.getUso());
        RequestBody precio = RequestBody.create(MediaType.parse("application/json"),inmueble.getPrecio()+"");
        RequestBody Disponibe = RequestBody.create(MediaType.parse("application/json"),inmueble.getDisponible()+"");

        String rutaArchivo = RealPathUtil.getRealPath(getApplication(),imageUri);
        File archivo = new File(rutaArchivo);

        RequestBody Imagen = RequestBody.create(MediaType.parse("multipart/form-data"),archivo);
        MultipartBody.Part ImagenFile = MultipartBody.Part.createFormData("imagenFile",archivo.getName(),Imagen);

        Call<Inmueble> call = api.crearInmueble(token,Direccion,
                Ambientes,
                Superficie,
                Tipo,
                Uso,
                precio,
                Disponibe,
                ImagenFile);
        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplication(),"Se agrego correctamente",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Toast.makeText(getApplication(),"No se Agrego correctamente",Toast.LENGTH_LONG).show();
                Log.d("falla",throwable.getMessage());
            }
        });
    }
}