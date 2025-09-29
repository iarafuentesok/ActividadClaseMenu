package com.example.actividadclasemenu.ui.eliminarProducto;

import static com.example.actividadclasemenu.MainActivity.listaProductos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.actividadclasemenu.modelo.Producto;

public class DetalleProductoViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> productoEliminado;
    private MutableLiveData<String> mensaje;

    public DetalleProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getProductoEliminado() {
        if (productoEliminado == null) {
            productoEliminado = new MutableLiveData<>();
        }
        return productoEliminado;
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public void eliminarProducto(String codigo) {
        if (productoEliminado == null) {
            productoEliminado = new MutableLiveData<>();
        }
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            mensaje.setValue("Código inválido");
            productoEliminado.setValue(false);
            return;
        }

        Producto productoParaEliminar = null;
        for (Producto producto : listaProductos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                productoParaEliminar = producto;
                break;
            }
        }

        if (productoParaEliminar != null) {
            listaProductos.remove(productoParaEliminar);
            mensaje.setValue("Producto eliminado correctamente");
            productoEliminado.setValue(true);
        } else {
            mensaje.setValue("El producto ya no existe");
            productoEliminado.setValue(false);
        }
    }
}