package com.example.tp_inmobiliaria_navarro.request;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.tp_inmobiliaria_navarro.modelo.CambiarClaveRequest;
import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Pago;
import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClientRetrofit {

    private SharedPreferences sharedPreferences;
    public static final String URL = "http://192.168.1.3:5000/";
    private static MisEndpoint mep;

    public static MisEndpoint getEndPoint(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mep= retrofit.create(MisEndpoint.class);
        return mep;

    }

    public interface  MisEndpoint
    {
        @FormUrlEncoded
        @POST("Propietarios/login")
        Call<String> login(@Field("Usuario")String u , @Field("Clave")String c);

        @GET("Propietarios")
        Call<Propietario> miPerfil(@Header("Authorization")String token);

        @PUT("Propietarios")
        Call<Propietario>modificarUsuario(@Header("Authorization")String token,@Body Propietario propietario);

        @FormUrlEncoded
        @POST("Propietarios/email")
        Call<Void> resetearPassword(@Field("email")String email);


        @PUT("Propietarios/cambiarClave")
        Call<Void> cambiarClave(@Header("Authorization") String token, @Body CambiarClaveRequest request);



        @GET("Inmuebles")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization")String token);

        @PUT("Inmuebles/CambiarEstado/{id}")
        Call<Inmueble> inmuebleCambiarDisponibilidad(@Path("id") int id , @Body Inmueble inmueble, @Header("Authorization") String token);

        @Multipart
        @POST("Inmuebles/crear")
        Call<Inmueble>crearInmueble(@Header("Authorization")String token,
                                    @Part("direccion") RequestBody direccion,
                                    @Part("ambientes")RequestBody ambientes,
                                    @Part("superficie")RequestBody superficie,
                                    @Part("tipo")RequestBody tipo,
                                    @Part("uso")RequestBody uso,
                                    @Part("precio")RequestBody precio,
                                    @Part("disponible")RequestBody disponible,
                                    @Part MultipartBody.Part ImagenFile);


        @GET("Contratos/traercontratos")
        Call<List<Contrato>> traerContratos(@Header("Authorization")String token);

        /*@GET("Contratos/{id}")
        Call<Contrato> obtenerContrato(@Header("Authorization")String token,@Path("id") int id);*/

        @GET("Pagos/traerpagos")
        Call<List<Pago>> traerPagos(@Header("Authorization")String token);




    }

    public static void guardarToken (String token, Context context){
        SharedPreferences sp = context.getSharedPreferences("auth_token",context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("token",token);
        ed.apply();

    }

    public static void borrarToken(Context context){
        SharedPreferences sp = context.getSharedPreferences("auth_token",context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("token",null);
        ed.apply();
    }

    public static String leerToken  (Context context) {
        SharedPreferences sp = context.getSharedPreferences("auth_token", context.MODE_PRIVATE);
        return sp.getString("token", null);
    }



}
