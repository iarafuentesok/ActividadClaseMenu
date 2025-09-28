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

    private FragmentAgregarProductosBinding binding;
    private AgregarProductosViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(AgregarProductosViewModel.class);
        binding = FragmentAgregarProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.inputCodigo.getText().toString();
                String descripcion = binding.inputDescripcion.getText().toString();
                String precio = binding.inputPrecio.getText().toString();
                vm.validateForm(codigo, descripcion, precio);
            }
        });

        vm.getErrorCodigo().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorCodigo.setText(error);
            binding.tvErrorCodigo.setVisibility(View.VISIBLE);
        });

        vm.getErrorDescripcion().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorDescripcion.setText(error);
            binding.tvErrorDescripcion.setVisibility(View.VISIBLE);
        });

        vm.getErrorPrecio().observe(getViewLifecycleOwner(), error -> {
            binding.tvErrorPrecio.setText(error);
            binding.tvErrorPrecio.setVisibility(View.VISIBLE);
        });

        vm.getExito().observe(getViewLifecycleOwner(), exito -> {
            Toast toast = Toast.makeText(getContext(), exito, Toast.LENGTH_LONG);
            limpiarCampos();
            toast.show();
        });

        return root;
    }

    private void limpiarCampos(){
        binding.inputCodigo.setText("");
        binding.inputDescripcion.setText("");
        binding.inputPrecio.setText("");

    }

}