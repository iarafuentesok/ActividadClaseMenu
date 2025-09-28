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

    private ListaProductosViewModel vm;
    private FragmentListaProductosBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ListaProductosViewModel.class);
        binding = FragmentListaProductosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getProductos().observe(getViewLifecycleOwner(), productos -> {
            ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());
            GridLayoutManager glm = new GridLayoutManager(getContext(), 1);
            binding.lista.setLayoutManager(glm);
            binding.lista.setAdapter(adapter);
        });
        vm.getNoHayProductos().observe(getViewLifecycleOwner(), noHayProductos -> {
            binding.tvError.setVisibility(View.VISIBLE);
            binding.tvError.setText(noHayProductos);
        });
        vm.cargarProductos();
        return root;
    }
}