package com.example.tp_inmobiliaria_navarro.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.modelo.Propietario;
import com.example.tp_inmobiliaria_navarro.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private  MutableLiveData<Propietario> propietarioM;
    private MutableLiveData<Boolean> Editable = new MutableLiveData<>(false);



    private ApiClient api = ApiClient.getApi();
    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application;

    }
    public LiveData<Propietario> getpropietarioM(){
        if(propietarioM == null){
            propietarioM = new MutableLiveData<>();
        }
        return propietarioM;
    }



    public  void LeerPropietario(){
        Propietario pro = api.obtenerUsuarioActual();
        Log.d("salida", String.valueOf(pro.getNombre()));
        if(pro!=null){
            propietarioM.setValue(pro);
        }
    }

    public void ActuPropietario(String id,String dni , String nombre,String apellido,String mail , String clave , String telefono,int avatar){
        Propietario propietario = new Propietario(Integer.parseInt(id),Long.parseLong(dni),nombre,apellido,mail,clave,telefono,avatar);
        api.actualizarPerfil(propietario);
        propietarioM.setValue(propietario);

    }



    public LiveData<Boolean> getEditable() {
        return Editable;
    }

    public void Actualizar() {
        Editable.setValue(!Editable.getValue());
    }








}