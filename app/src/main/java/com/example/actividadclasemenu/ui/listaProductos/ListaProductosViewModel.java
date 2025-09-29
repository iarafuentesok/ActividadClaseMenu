package com.example.actividadclasemenu.ui.listaProductos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.actividadclasemenu.MainActivity;
import com.example.actividadclasemenu.modelo.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class ListaProductosViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Producto>> productos; // LiveData que contiene la lista de productos listos para ser mostrados en pantalla.
    public MutableLiveData<String> noHayProductos; // LiveData utilizado para informar a la vista cuando no hay elementos disponibles.

    public ListaProductosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Producto>> getProductos(){
        if(productos == null){
            productos = new MutableLiveData<>();
        }
        return productos;
    }

    public LiveData<String> getNoHayProductos(){
        if(noHayProductos == null){
            noHayProductos = new MutableLiveData<>();
        }
        return noHayProductos;
    }

    // Copia auxiliar de la lista compartida para poder manipularla sin afectar el origen directamente.

    ArrayList<Producto> lista = new ArrayList<>(MainActivity.listaProductos);





    public void cargarProductos() {
        if(lista.isEmpty()){
            // Si no hay datos, informamos a la vista para que muestre el mensaje correspondiente.
            noHayProductos.setValue("No hay productos");
        } else {
            // Se crea una copia para ordenar sin alterar la lista original mantenida en MainActivity.
            ArrayList<Producto> listaOrdenada = new ArrayList<>(MainActivity.listaProductos);
            // Ordena los productos alfabéticamente por descripción antes de enviarlos a la vista.
            listaOrdenada.sort(new Comparator<Producto>() {
                @Override
                public int compare(Producto p1, Producto p2) {
                    return p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion());
                }
            });
            productos.setValue(listaOrdenada);
        }
    }

}