package com.example.actividadclasemenu.ui.eliminarProducto;

import static com.example.actividadclasemenu.MainActivity.listaProductos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.actividadclasemenu.modelo.Producto;

public class EliminarProductoViewModel extends AndroidViewModel {

    private MutableLiveData<String> errorCodigo;
    private MutableLiveData<Producto> productoEncontrado;

    public EliminarProductoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getErrorCodigo() {
        if (errorCodigo == null) {
            errorCodigo = new MutableLiveData<>();
        }
        return errorCodigo;
    }

    public LiveData<Producto> getProductoEncontrado() {
        if (productoEncontrado == null) {
            productoEncontrado = new MutableLiveData<>();
        }
        return productoEncontrado;
    }

    public void buscarProducto(String codigo) {
        if (errorCodigo == null) {
            errorCodigo = new MutableLiveData<>();
        }
        if (productoEncontrado == null) {
            productoEncontrado = new MutableLiveData<>();
        }

        if (codigo == null) {
            codigo = "";
        }
        codigo = codigo.trim();

        if (codigo.isEmpty()) {
            errorCodigo.setValue("El código no puede estar vacío");
            productoEncontrado.setValue(null);
            return;
        }

        Producto encontrado = null;
        for (Producto producto : listaProductos) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = producto;
                break;
            }
        }

        if (encontrado == null) {
            errorCodigo.setValue("No se encontró un producto con ese código");
            productoEncontrado.setValue(null);
        } else {
            errorCodigo.setValue("");
            productoEncontrado.setValue(encontrado);
        }
    }

    public void limpiarProductoEncontrado() {
        if (productoEncontrado != null) {
            productoEncontrado.setValue(null);
        }
    }
}