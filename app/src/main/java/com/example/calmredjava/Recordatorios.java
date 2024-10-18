package com.example.calmredjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Recordatorios extends Fragment {

    private Switch switchNotificaciones;
    private LinearLayout layoutNotificaciones;
    private TextView notificacionPermanente, notificacionTemporal;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recordatorios, container, false);

        // Inicia la interaccion para ver las notis
        switchNotificaciones = view.findViewById(R.id.switchNotificaciones);
        layoutNotificaciones = view.findViewById(R.id.layoutNotificaciones);
        notificacionPermanente = view.findViewById(R.id.notificacionPermanente);
        notificacionTemporal = view.findViewById(R.id.notificacionTemporal);

        //Si esta activado el swich muestra las notis
        mostrarNotificaciones(switchNotificaciones.isChecked());

        // Listener para activar/desactivar en la pantalla las notis
        switchNotificaciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mostrarNotificaciones(isChecked);
            }
        });

        // Intento de hacer la parte de las configuraciones
        configurarNotificaciones();

        return view;
    }

    private void mostrarNotificaciones(boolean activar) {
        if (activar) {
            layoutNotificaciones.setVisibility(View.VISIBLE);
        } else {
            layoutNotificaciones.setVisibility(View.GONE);
        }
    }

    private void configurarNotificaciones() {
        // Notificacion permanente: sugerencia para crear un grupo
        notificacionPermanente.setText("¿Te gustaria crear un nuevo grupo de meditacion?");

        // Notificacion temporal: sugerencia para hacer un comentario en el chat general
        notificacionTemporal.setText("¡Unete a la conversacion en el chat general y comparte tus pensamientos!");

    }
}