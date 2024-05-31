package com.example.tp_inmobiliaria_navarro.ui.logout;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

public class Logout {

    public static void salirDialogo(Activity context) {

        new AlertDialog.Builder(context)
                .setTitle("Cierra de sesión")
                .setMessage("¿ Está seguro que desea cerrar la aplicacion ?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      ApiClientRetrofit.borrarToken(context);
                        ((AppCompatActivity) context).finishAndRemoveTask();
                        context.finishAffinity();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Contimnuamos", Toast.LENGTH_LONG).show();
                    }
                })
                .show();

    }




    }

