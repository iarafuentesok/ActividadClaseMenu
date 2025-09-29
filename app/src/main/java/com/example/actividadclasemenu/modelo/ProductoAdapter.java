package com.example.actividadclasemenu.modelo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actividadclasemenu.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {
    // Esta e la colección de productos que se mostrarán en el RecyclerView.

    public static List<Producto> productos;
    private Context context;

    //convierte layouts xml en vistas
    private LayoutInflater li;

    public ProductoAdapter(List<Producto> productos, Context context, LayoutInflater li) {
        this.context = context;
        this.productos = productos;
        this.li = li;
    }


    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = li.inflate(R.layout.productcard, parent, false);
        return new ViewHolderProducto(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        // Enlazamos los datos de cada producto con los TextView correspondientes.
        holder.tvCodigo.setText(productos.get(position).getCodigo());
        holder.tvDescripcion.setText(productos.get(position).getDescripcion());
        holder.tvPrecio.setText(String.valueOf(productos.get(position).getPrecio()));

    }

    @Override
    public int getItemCount() {
        // Devuelve la cantidad de elementos que se mostrarán en la lista.
        return productos.size();
    }


    public class ViewHolderProducto extends RecyclerView.ViewHolder{

        // Referencias a las vistas que componen cada tarjeta de producto.
        TextView tvCodigo;
        TextView tvDescripcion;
        TextView tvPrecio;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            // Asociamos cada vista con su correspondiente ID del layout.
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);

        }
    }
}


