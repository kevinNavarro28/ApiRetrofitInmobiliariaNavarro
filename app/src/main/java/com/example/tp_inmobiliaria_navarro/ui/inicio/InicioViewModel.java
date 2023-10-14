package com.example.tp_inmobiliaria_navarro.ui.inicio;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<MapaActual> mMapa;

    private static final LatLng ULP=new LatLng(-33.150720,-66.306864);


    public InicioViewModel(@NonNull Application application) {
        super(application);
        this.context=application;



    }

    public LiveData<MapaActual> getMMapa(){

        if(mMapa==null){
            mMapa=new MutableLiveData<>();
        }

        return mMapa;
    }


    public void obtenerMapa(){
        MapaActual ma=new MapaActual();
        mMapa.setValue(ma);
    }

    public class MapaActual implements OnMapReadyCallback{


        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

            googleMap.addMarker(new MarkerOptions().position(ULP).title("Inmobiliaria Navarro"));
            CameraPosition camPos=new CameraPosition.Builder()
                    .target(ULP)//donde aparecera
                    .zoom(19)
                    .bearing(45)//inclinacion
                    .tilt(70)
                    .build();// lo crea
            CameraUpdate update= CameraUpdateFactory.newCameraPosition(camPos);
            googleMap.animateCamera(update);
        }
    }
}