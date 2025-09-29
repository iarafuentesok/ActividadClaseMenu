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
    // LiveData que expone el mensaje de error específico para el campo código.
    MutableLiveData<String> errorDescripcion;
    // LiveData para comunicar errores relacionados con la descripción.

    MutableLiveData<String> errorPrecio;
    // LiveData que notifica los problemas detectados en el precio ingresado.

    MutableLiveData<String> exito;
    // LiveData que emite un mensaje informativo cuando el producto se agrega correctamente.


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
        // Se eliminan espacios en blanco al inicio y final para evitar errores de validación.
        codigo = codigo.trim();
        descripcion = descripcion.trim();
        precioTexto = precioTexto.trim();
        // Bandera que controla si se detectó algún error en la validación.
        boolean hayErrores = false;

        if(codigo.isEmpty()){
            errorCodigo.setValue("El código no puede estar vacío");
            hayErrores = true;
        } else { // Se verifica que el código ingresado no esté repetido en la lista existente.
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
                // En caso de que el usuario ingrese letras u otros caracteres no numéricos.
                errorPrecio.setValue("Formato de precio inválido");
                hayErrores = true;
            }
        }


        if(!hayErrores){
            // Se limpian los mensajes previos y se agrega el nuevo producto a la lista general.
            limpiarErrores();
            Producto producto = new Producto(codigo, descripcion, precio);
            listaProductos.add(producto);
            exito.setValue("Producto agregado exitosamente");
        }
    }

    // Restablece los mensajes de error para que la interfaz quede sin advertencias visibles.
    private void limpiarErrores(){
        errorCodigo.setValue("");
        errorDescripcion.setValue("");
        errorPrecio.setValue("");
    }
}