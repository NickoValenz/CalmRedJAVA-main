package com.example.calmredjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void irFeedInicioSesion(View v){

        EditText campo1nombre = this.findViewById(R.id.nombreLogin);
        String nombre = campo1nombre.getText().toString();

        EditText campo2correo = this.findViewById(R.id.correo);
        String correo = campo2correo.getText().toString();

        EditText campo3contrasenia = this.findViewById(R.id.contraseniaLogin);
        String contrasenia = campo3contrasenia.getText().toString();

        if(nombre.equals("admin") && contrasenia.equals("admin"))
        {

        Intent i = new Intent(this,FeedPrincipal.class);
        startActivity(i);}else{
            Toast.makeText(this, "Error de coincidencia credenciales", Toast.LENGTH_SHORT).show();
        }

    }
}