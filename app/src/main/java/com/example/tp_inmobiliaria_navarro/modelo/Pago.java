package com.example.tp_inmobiliaria_navarro.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int nroPago;
    private Contrato contrato;
    private double importe;
    private String fechaPago;



    public Pago(int id, int nroPago, Contrato contrato, double importe, String fechaPago) {
        this.id = id;
        this.nroPago = nroPago;
        this.contrato = contrato;
        this.importe = importe;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return id;
    }

    public void setIdPago(int idP) {
        this.id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }
}
