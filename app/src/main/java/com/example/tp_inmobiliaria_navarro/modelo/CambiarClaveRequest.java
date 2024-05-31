package com.example.tp_inmobiliaria_navarro.modelo;

import com.google.gson.annotations.SerializedName;

public class CambiarClaveRequest {
    @SerializedName("NuevaClave")
    private String nuevaClave;

    public CambiarClaveRequest(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }
}
