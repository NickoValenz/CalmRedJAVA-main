package com.example.calmredjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class FeedPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feed_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            TabLayout tl = (TabLayout) findViewById(R.id.tablayout);
            tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    // Eventos que se ejeceuten al dar tap como a una pestaña del appbar
                    int position = tab.getPosition();
                    switch (position) {
                        case 0:
                            //LAMAR AL fragment diario por que es mi primero
                            FrDiario d = new FrDiario();
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,d).commit();
                            break;
                        case 1:
                            //LLAMAR AL fragment buscar por que es mi segundo
                            Buscar b = new Buscar();
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,b).commit();
                            break;
                        case 2:
                            //LAMAR al fragment grupos por que es mi tercero
                            FragmentoGrupos g = new FragmentoGrupos();
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,g).commit();
                            break;
                        case 3:
                            //LLAMAR al fragment recordatorios por que es mi cuarto
                            Recordatorios r = new Recordatorios();
                            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,r).commit();
                            break;
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
                    // Podemos ejecutar cosas en este estado inactivo de la pestaña
                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    // Mostrar o ejecutar cosas cuando se vuelva a seleccionar esa pestaña

                }
            });
            return insets;
        });
    }
    public void irDiario(View v){
        Intent i = new Intent(this,Diario.class);
        startActivity(i);
    }
    public void irGrupos(View v){
        Intent i = new Intent(this,Grupos.class);
        startActivity(i);}

    public void irPerfilUsuario1(View v){
        Intent i = new Intent(this,PerfilUsuario1.class);
        startActivity(i);}

    public void irPerfilExter2(View v){
        Intent i = new Intent(this,PerfilExter2.class);
        startActivity(i);}

    public void irRecordatorios(View v){
        Intent i = new Intent(this,RecordatoriosConfig.class);
        startActivity(i);}

    public void irChatGeneral(View v){
        Intent i = new Intent(this,ChatGeneral.class);
        startActivity(i);}

    public void irPagBusqueda(View v){
        Intent i = new Intent(this,PagBusqueda.class);
        startActivity(i);}
}