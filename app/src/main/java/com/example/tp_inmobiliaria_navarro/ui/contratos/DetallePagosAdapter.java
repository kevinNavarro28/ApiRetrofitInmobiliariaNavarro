package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Pago;

import java.util.List;

public class DetallePagosAdapter extends RecyclerView.Adapter<com.example.tp_inmobiliaria_navarro.ui.contratos.DetallePagosAdapter.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;

    private List<com.example.tp_inmobiliaria_navarro.modelo.Pago> Pago ;

    public  DetallePagosAdapter(Context context, List<Pago>Pago, LayoutInflater inflater){
        this.context= context;
        this.Pago = Pago;
        this.inflater=inflater;

    }

    @NonNull
    @Override
    public com.example.tp_inmobiliaria_navarro.ui.contratos.DetallePagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_view_pagos, parent, false);
        return new DetallePagosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.tp_inmobiliaria_navarro.ui.contratos.DetallePagosAdapter.ViewHolder holder, int position) {
        holder.codigopago.setText(Pago.get(position).getIdPago()+"");
        holder.numeropago.setText(Pago.get(position).getNumero()+"");
        holder.condigocontrato.setText(Pago.get(position).getContrato().getIdContrato()+"");
        holder.importe.setText(Pago.get(position).getImporte()+"");
        holder.fechadedpago.setText(Pago.get(position).getFechaDePago());

    }

    @Override
    public int getItemCount( ) {
        return Pago.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView codigopago, numeropago, condigocontrato, importe, fechadedpago;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codigopago = itemView.findViewById(R.id.TvCodigoPago);
            numeropago = itemView.findViewById(R.id.TvNumdePago);
            condigocontrato = itemView.findViewById(R.id.TvCodigodecontrato);
            importe = itemView.findViewById(R.id.TvImporte);
            fechadedpago = itemView.findViewById(R.id.TvFechadePago);
        }
    }

}