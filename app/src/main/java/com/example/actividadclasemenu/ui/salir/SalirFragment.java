package com.example.actividadclasemenu.ui.salir;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.actividadclasemenu.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;    // Binding para acceder a los elementos del layout del fragmento.
    private SalirViewModel vm;    // ViewModel reservado para manejar cualquier lógica asociada con salir de la app.


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {SalirViewModel slideshowViewModel = new ViewModelProvider(this).get(SalirViewModel.class);

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(SalirViewModel.class);
        View root = binding.getRoot();
        // Muestra el diálogo pedido por el profe preguntando al usuario si realmente desea salir de la aplicación.
        mostrarDialogoSalir();


        return root;
    }

    private void mostrarDialogoSalir() {
        // Construye un diálogo de confirmación con opciones para cerrar o continuar usando la app.
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setIcon(android.R.drawable.ic_lock_power_off) // Ícono diferente
                .setTitle("Cerrar aplicación")
                .setMessage("¿Querés salir de la app ahora o seguir usando?")
                .setPositiveButton("Salir", (dialogInterface, which) -> {
                    Toast.makeText(requireContext(), "Cerrando aplicación...", Toast.LENGTH_SHORT).show();
                    requireActivity().finishAffinity();
                })
                .setNegativeButton("Seguir aquí", (dialogInterface, which) -> {
                    requireActivity().onBackPressed();
                    Toast.makeText(requireContext(), "Bien! Continuá usando la app", Toast.LENGTH_SHORT).show();
                })

                .create();

        // Que no se pueda cerrar tocando fuera del diálogo
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Liberamos el binding para evitar fugas de memoria cuando la vista se destruye.
        binding = null;
    }
}