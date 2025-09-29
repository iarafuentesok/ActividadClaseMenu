package com.example.actividadclasemenu.ui.eliminarProducto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.actividadclasemenu.R;
import com.example.actividadclasemenu.databinding.FragmentEliminarProductoBinding;
import com.example.actividadclasemenu.modelo.Producto;

public class EliminarProductoFragment extends Fragment {

    private FragmentEliminarProductoBinding binding;
    private EliminarProductoViewModel vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
                .create(EliminarProductoViewModel.class);
        binding = FragmentEliminarProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnBuscar.setOnClickListener(v -> {
            String codigo = binding.inputCodigo.getText().toString();
            vm.buscarProducto(codigo);
        });

        vm.getErrorCodigo().observe(getViewLifecycleOwner(), error -> {
            if (error == null || error.isEmpty()) {
                binding.tvErrorCodigo.setVisibility(View.GONE);
            } else {
                binding.tvErrorCodigo.setText(error);
                binding.tvErrorCodigo.setVisibility(View.VISIBLE);
            }
        });

        vm.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                navegarADetalle(producto);
                vm.limpiarProductoEncontrado();
            }
        });

        return root;
    }

    private void navegarADetalle(Producto producto) {
        Bundle args = new Bundle();
        args.putString("codigo", producto.getCodigo());
        args.putString("descripcion", producto.getDescripcion());
        args.putDouble("precio", producto.getPrecio());
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_nav_delete_to_detalleProductoFragment, args);
    }
}