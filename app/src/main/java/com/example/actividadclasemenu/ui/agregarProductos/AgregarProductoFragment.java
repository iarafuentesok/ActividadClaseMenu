package com.example.actividadclasemenu.ui.agregarProductos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.actividadclasemenu.databinding.FragmentAgregarProductosBinding;

public class AgregarProductoFragment extends Fragment {
    // aca se referencia al binding para acceder a las vistas del formulario.
    private FragmentAgregarProductosBinding binding;
    private AgregarProductosViewModel vm; //nuestro viewmodel

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Obtenemos una instancia del AgregarProductosViewModel asociado al ciclo de vida de la aplicación.
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarProductosViewModel.class);
        //se usa para acceder a los elementos de la vista sin usar findViewById
        binding = FragmentAgregarProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

// Configuramos el botón para intentar guardar el producto cuando el usuario hace clic.
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.inputCodigo.getText().toString();
                String descripcion = binding.inputDescripcion.getText().toString();
                String precio = binding.inputPrecio.getText().toString();
                // Se delega al ViewModel la validación de los datos capturados.
                vm.validateForm(codigo, descripcion, precio);
            }
        });
        // Observa los mensajes de error de código para mostrarlos en pantalla.
        vm.getErrorCodigo().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorCodigo.setText(error);
            binding.tvErrorCodigo.setVisibility(View.VISIBLE);
        });
        // Observa los mensajes de error de descripción para informar al usuario.
        vm.getErrorDescripcion().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorDescripcion.setText(error);
            binding.tvErrorDescripcion.setVisibility(View.VISIBLE);
        });
        // Observa los mensajes de error de precio para guiar la corrección.
        vm.getErrorPrecio().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorPrecio.setText(error);
            binding.tvErrorPrecio.setVisibility(View.VISIBLE);
        });
        // Si la validación se completa con éxito, se muestra un mensaje y se limpian los campos.
        vm.getExito().observe(getViewLifecycleOwner(), exito -> {
            Toast toast = Toast.makeText(getContext(), exito, Toast.LENGTH_LONG);
            limpiarCampos();
            toast.show();
        });

        return root;
    }

    // Restablece el formulario a su estado inicial una vez agregado el producto.
    private void limpiarCampos(){
        binding.inputCodigo.setText("");
        binding.inputDescripcion.setText("");
        binding.inputPrecio.setText("");

    }

}