package com.example.actividadclasemenu.ui.agregarProductos;
import static com.example.actividadclasemenu.MainActivity.listaProductos;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.actividadclasemenu.modelo.Producto;
public class AgregarProductosViewModel extends AndroidViewModel {

    MutableLiveData<String> errorCodigo;
    MutableLiveData<String> errorDescripcion;
    MutableLiveData<String> errorPrecio;
    MutableLiveData<String> exito;


    public AgregarProductosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getErrorCodigo(){
        if(errorCodigo == null){
            errorCodigo = new MutableLiveData<>();
        }
        return errorCodigo;
    }

    public LiveData<String> getErrorDescripcion(){
        if(errorDescripcion == null){
            errorDescripcion = new MutableLiveData<>();
        }
        return errorDescripcion;
    }

    public LiveData<String> getErrorPrecio(){
        if(errorPrecio == null){
            errorPrecio = new MutableLiveData<>();
        }
        return errorPrecio;
    }

    public LiveData<String> getExito(){
        if(exito == null){
            exito = new MutableLiveData<>();
        }
        return exito;
    }

    public void validateForm(String codigo, String descripcion, String precioTexto){

        codigo = codigo.trim();
        descripcion = descripcion.trim();
        precioTexto = precioTexto.trim();

        boolean hayErrores = false;

        if(codigo.isEmpty()){
            errorCodigo.setValue("El código no puede estar vacío");
            hayErrores = true;
        } else {
            for (Producto producto : listaProductos){
                if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                    errorCodigo.setValue("El código ya existe");
                    hayErrores = true;
                    break;
                }
            }
        }


        if(descripcion.isEmpty()){
            errorDescripcion.setValue("La descripción no puede estar vacía");
            hayErrores = true;
        }

        float precio = 0f;
        if(precioTexto.isEmpty()){
            errorPrecio.setValue("El precio no puede estar vacío");
            hayErrores = true;
        } else {
            try {
                precio = Float.parseFloat(precioTexto);
                if(precio <= 0){
                    errorPrecio.setValue("El precio debe ser mayor a 0");
                    hayErrores = true;
                }
            } catch (NumberFormatException e) {
                errorPrecio.setValue("Formato de precio inválido");
                hayErrores = true;
            }
        }


        if(!hayErrores){
            limpiarErrores();
            Producto producto = new Producto(codigo, descripcion, precio);
            listaProductos.add(producto);
            exito.setValue("Producto agregado exitosamente");
        }
    }

    private void limpiarErrores(){
        errorCodigo.setValue("");
        errorDescripcion.setValue("");
        errorPrecio.setValue("");
    }
}