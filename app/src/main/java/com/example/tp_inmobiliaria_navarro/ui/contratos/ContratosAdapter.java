package com.example.tp_inmobiliaria_navarro.ui.contratos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.bumptech.glide.request.RequestOptions;
import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.modelo.Contrato;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;
import com.example.tp_inmobiliaria_navarro.request.ApiClientRetrofit;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContratosAdapter extends RecyclerView.Adapter<com.example.tp_inmobiliaria_navarro.ui.contratos.ContratosAdapter.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Contrato> listaContrato;



    public ContratosAdapter(Context context, ArrayList<Contrato> listaContrato, LayoutInflater inflater){
        this.context= context;
        this.listaContrato = listaContrato;
        this.inflater=inflater;

    }

    @NonNull
    @Override
    public com.example.tp_inmobiliaria_navarro.ui.contratos.ContratosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_view_contratos, parent, false);
        return new ContratosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.tp_inmobiliaria_navarro.ui.contratos.ContratosAdapter.ViewHolder holder, int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.inmueble)
                .error(R.drawable.inmueble);
        Glide.with(context)
                .load(ApiClientRetrofit.URL+listaContrato.get(position).getInmueble().getImagenUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .into(holder.foto);
        holder.direccion.setText(listaContrato.get(position).getInmueble().getDireccion());

    }

    @Override
    public int getItemCount( ) {
        return listaContrato.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;

        TextView direccion;

        Button ver;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.ImgContratos);
            direccion=itemView.findViewById(R.id.TvDireccionContratos);
            ver=itemView.findViewById(R.id.BtVerContratos);

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Contrato contrato = listaContrato.get(getLayoutPosition());
                    bundle.putSerializable("contrato",contrato);
                    Navigation.findNavController(v).navigate(R.id.action_nav_contratos_to_contratosResultado,bundle);
                }
            });
        }
    }

}