package com.example.actividadclasemenu.ui.listaProductos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.actividadclasemenu.R;

import com.example.actividadclasemenu.databinding.FragmentListaProductosBinding;
import com.example.actividadclasemenu.modelo.ProductoAdapter;

public class ListaProductoFragment extends Fragment {


    private ListaProductosViewModel vm;     // ViewModel encargado de proveer la lista de productos a la vista.
    private FragmentListaProductosBinding binding;     // Binding generado para interactuar con los elementos del layout sin llamadas repetidas a findViewById.


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaProductosViewModel.class);
        binding = FragmentListaProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        // Observamos la lista de productos para actualizar el RecyclerView cada vez que cambie.
        vm.getProductos().observe(getViewLifecycleOwner(), productos -> {
            ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(adapter);
        });
        // Observa si no existen productos cargados y muestra un mensaje informativo.
        vm.getNoHayProductos().observe(getViewLifecycleOwner(), noHayProductos -> {
            binding.tvError.setVisibility(View.VISIBLE);
            binding.tvError.setText(noHayProductos);
        });
        // Solicita al ViewModel que obtenga los datos actuales para mostrarlos.
        vm.cargarProductos();
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}