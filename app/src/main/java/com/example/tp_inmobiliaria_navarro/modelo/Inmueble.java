package com.example.tp_inmobiliaria_navarro.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private int ambientes;

    private int superficie;

    private String tipo;
    private String uso;

    private double precio;
    private Propietario propietario;
    //En falso significa que el innmueble no está disponible por alguna falla en el mismo.
    private boolean disponible;

    private String imagenUrl;

    public Inmueble() {
        this.id=id;
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.disponible = disponible;
        this.imagenUrl = imagenUrl;
    }


    public Inmueble(String direccion, int ambientes, int superficie, String tipo, String uso, double precio, Boolean disponible) {
        this.direccion = direccion;
        this.ambientes = ambientes;
        this.superficie = superficie;
        this.tipo = tipo;
        this.uso = uso;
        this.precio = precio;
        this.disponible = disponible;

    }




    public int getIdInmueble() {
        return id;
    }

    public void setIdInmueble(int idInmueble) {
        this.id = idInmueble;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public int getSuperficie(){return superficie;}

    public void setSuperficie(int superficie){this.superficie=superficie;}

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagen) {
        this.imagenUrl = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
