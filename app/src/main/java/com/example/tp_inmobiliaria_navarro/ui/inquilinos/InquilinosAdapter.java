package com.example.tp_inmobiliaria_navarro.ui.inquilinos;

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
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;
import com.example.tp_inmobiliaria_navarro.modelo.Inquilino;

import java.util.List;

public class InquilinosAdapter extends RecyclerView.Adapter<InquilinosAdapter.ViewHolder>  {

    private Context context;
    private LayoutInflater inflater;

    private List<Inmueble> Inmueble ;

    public InquilinosAdapter(Context context,List<Inmueble> Inmueble,LayoutInflater inflater){
        this.context= context;
        this.Inmueble =Inmueble;
        this.inflater=inflater;

    }

    @NonNull
    @Override
    public InquilinosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_view_inquilinos, parent, false);
        return new InquilinosAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinosAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(Inmueble.get(position).getImagenUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.foto);
        holder.direccion.setText(Inmueble.get(position).getDireccion());

    }

    @Override
    public int getItemCount( ) {
        return Inmueble.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;

        TextView direccion;

        Button ver;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foto=itemView.findViewById(R.id.ImgInquilinos);
            direccion=itemView.findViewById(R.id.TvDireccionInquilinos);
            ver=itemView.findViewById(R.id.BtVerInquilinos);

            ver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    Inmueble inmu = Inmueble.get(getLayoutPosition());
                    bundle.putSerializable("inmueble",inmu);
                    Navigation.findNavController(v).navigate(R.id.action_nav_inquilinos_to_inquilinosResultado,bundle);
                }
            });
        }
    }

}

