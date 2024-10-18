package com.example.calmredjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Grupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_grupos2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void irCreacionGrupos(View v){
        Intent i = new Intent(this,CreacionGrupos.class);
        startActivity(i);}

    public void irGrupos(View v){
        Intent i = new Intent(this,Grupos.class);
        startActivity(i);}

    public void irDiario(View v){
        Intent i = new Intent(this,Diario.class);
        startActivity(i);}

    public void irPagBusqueda(View v){
        Intent i = new Intent(this,PagBusqueda.class);
        startActivity(i);}

    public void irRecordatorios(View v){
        Intent i = new Intent(this,RecordatoriosConfig.class);
        startActivity(i);}
}