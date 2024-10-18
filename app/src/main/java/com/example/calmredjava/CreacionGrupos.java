package com.example.calmredjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreacionGrupos extends AppCompatActivity {

    private EditText nombreGrupoEditText;
    private RadioGroup etiquetasRadioGroup;
    private Button btnGuardarGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);

        nombreGrupoEditText = findViewById(R.id.nombreGrupoEditText);
        etiquetasRadioGroup = findViewById(R.id.etiquetasRadioGroup);
        btnGuardarGrupo = findViewById(R.id.btnGuardarGrupo);

        btnGuardarGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreGrupo = nombreGrupoEditText.getText().toString().trim();
                int selectedRadioButtonId = etiquetasRadioGroup.getCheckedRadioButtonId();

                if (nombreGrupo.isEmpty()) {
                    Toast.makeText(CreacionGrupos.this, "Por favor ingresa un nombre para el grupo", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedRadioButtonId == -1) {
                    Toast.makeText(CreacionGrupos.this, "Por favor selecciona una etiqueta", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String etiqueta = selectedRadioButton.getText().toString();

                // Con esta sentencia guardo el nombre y la etiqueta o tipo de grupo
                Toast.makeText(CreacionGrupos.this, "Grupo creado: " + nombreGrupo + " - " + etiqueta, Toast.LENGTH_SHORT).show();

                // Se termina la creacion con esta sentencia
                finish();
            }
        });
    }
}
