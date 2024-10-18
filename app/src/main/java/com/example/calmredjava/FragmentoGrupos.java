package com.example.calmredjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class FragmentoGrupos extends Fragment {

    private ListView listViewGrupos;
    private Button btnCrearGrupo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento_grupos, container, false);

        listViewGrupos = view.findViewById(R.id.listViewGrupos);
        btnCrearGrupo = view.findViewById(R.id.btnCrearGrupo);

        // Lista de ejemplo de grupos
        ArrayList<String> grupos = new ArrayList<>();
        grupos.add("Grupo 1 - Meditación");
        grupos.add("Grupo 2 - Relajación");

        // Adaptador para mostrar la lista de grupos
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, grupos);
        listViewGrupos.setAdapter(adapter);

        // Acción del botón para crear un grupo
        btnCrearGrupo.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreacionGrupos.class);
            startActivity(intent);
        });

        return view;
    }
}
