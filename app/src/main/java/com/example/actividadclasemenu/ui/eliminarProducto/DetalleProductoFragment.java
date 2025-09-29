package com.example.actividadclasemenu.ui.eliminarProducto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.actividadclasemenu.R;
import com.example.actividadclasemenu.databinding.FragmentDetalleProductoBinding;

import java.util.Locale;

public class DetalleProductoFragment extends Fragment {

    private FragmentDetalleProductoBinding binding;
    private DetalleProductoViewModel vm;
    private String codigoProducto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())
                .create(DetalleProductoViewModel.class);
        binding = FragmentDetalleProductoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle args = getArguments();
        if (args != null) {
            codigoProducto = args.getString("codigo", "");
            String descripcion = args.getString("descripcion", "");
            double precio = args.getDouble("precio", 0);

            binding.tvCodigoValor.setText(codigoProducto);
            binding.tvDescripcionValor.setText(descripcion);
            binding.tvPrecioValor.setText(String.format(Locale.getDefault(), "$ %.2f", precio));
        }

        binding.btnConfirmarEliminacion.setOnClickListener(v -> vm.eliminarProducto(codigoProducto));

        vm.getMensaje().observe(getViewLifecycleOwner(), mensaje -> {
            if (mensaje != null && !mensaje.isEmpty()) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        vm.getProductoEliminado().observe(getViewLifecycleOwner(), eliminado -> {
            if (Boolean.TRUE.equals(eliminado)) {
                navegarALaLista();
            }
        });

        return root;
    }

    private void navegarALaLista() {
        NavController navController = Navigation.findNavController(binding.getRoot());
        NavOptions navOptions = new NavOptions.Builder()
                .setPopUpTo(R.id.nav_delete, true)
                .build();
        navController.navigate(R.id.nav_home, null, navOptions);
    }
}

