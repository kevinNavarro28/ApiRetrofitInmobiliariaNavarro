package com.example.tp_inmobiliaria_navarro.ui.inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tp_inmobiliaria_navarro.R;
import com.example.tp_inmobiliaria_navarro.modelo.Inmueble;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmuebleAdapter.ViewHolder> {

        private Context context;
        private LayoutInflater inflater;

        private List<Inmueble> Inmueble;

        public InmuebleAdapter(Context context,List<Inmueble> Inmueble,LayoutInflater inflater){
            this.context= context;
            this.Inmueble=Inmueble;
            this.inflater=inflater;

        }

        @NonNull
        @Override
        public com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View root = inflater.inflate(R.layout.item_view_inmuebles, parent, false);
            return new com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmuebleAdapter.ViewHolder(root);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.tp_inmobiliaria_navarro.ui.inmuebles.InmuebleAdapter.ViewHolder holder, int position) {

            Glide.with(context).load(Inmueble.get(position).getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.foto);
            holder.direccion.setText(Inmueble.get(position).getDireccion());
            double precio = Inmueble.get(position).getPrecio();
            String precioString = String.valueOf(precio);
            holder.precio.setText(precioString);



        }

        @Override
        public int getItemCount( ) {
            return Inmueble.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView foto;

            TextView direccion, precio;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                foto = itemView.findViewById(R.id.ImgInmuebles);
                direccion = itemView.findViewById(R.id.TvDireccionInmuebles);
                precio = itemView.findViewById(R.id.TvPrecioInmuebles);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        Inmueble inmu = Inmueble.get(getLayoutPosition());
                        bundle.putSerializable("inmueble",inmu);
                        Navigation.findNavController(v).navigate(R.id.action_nav_inmuebles_to_inmueblesResultadoFragment,bundle);
                    }
                });

            }
        }

}


