package com.example.calmredjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.calmredjava.modelos.EntradaDiario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FrDiario extends Fragment {

    private EditText tituloEditText, contenidoEditText;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fr_diario, container, false);

        tituloEditText = view.findViewById(R.id.tituloEditText);
        contenidoEditText = view.findViewById(R.id.contenidoEditText);
        Button btnGuardarEntrada = view.findViewById(R.id.btnGuardarEntrada);

        //inicia la referencia de Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("EntradasDiario");

        // boton para guardar la entrada una vez terminada
        btnGuardarEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarEntrada();
            }
        });

        return view;
    }

    private void guardarEntrada() {
        String titulo = tituloEditText.getText().toString().trim();
        String contenido = contenidoEditText.getText().toString().trim();

        if (titulo.isEmpty()) {
            tituloEditText.setError("El título es obligatorio");
            return;
        }

        if (contenido.isEmpty()) {
            contenidoEditText.setError("El contenido es obligatorio");
            return;
        }

        //Aqui se crea una clave unica para cada entrada para Firebase
        String idEntrada = databaseReference.push().getKey();

        // Crea el objeto EntradaDiario donde guardo los parametros del diario desde el modulos
        EntradaDiario entrada = new EntradaDiario(idEntrada, titulo, contenido);

        // Guardar la entrada en Firebase
        if (idEntrada != null) {
            databaseReference.child(idEntrada).setValue(entrada)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Entrada guardada", Toast.LENGTH_SHORT).show();
                            tituloEditText.setText("");
                            contenidoEditText.setText("");
                        } else {
                            Toast.makeText(getContext(), "Error al guardar la entrada", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}