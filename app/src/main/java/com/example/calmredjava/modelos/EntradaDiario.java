package com.example.calmredjava.modelos;

public class EntradaDiario {

    private String id;
    private String titulo;
    private String contenido;

    // Constructor vacío requerido para Firebase
    public EntradaDiario() {}

    public EntradaDiario(String id, String titulo, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
