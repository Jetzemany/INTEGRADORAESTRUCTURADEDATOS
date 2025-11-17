package com.example.Integradora.models;

/*
 * Operador con ID autogenerado y estado ocupado/libre.
 */
public class Operador {
    private static int CONTADOR = 1;
    private final int id;
    private final String nombre;
    private boolean ocupado;

    public Operador(String nombre) {
        this.id = CONTADOR++;
        this.nombre = nombre;
        this.ocupado = false;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean estaOcupado() { return ocupado; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }

    @Override
    public String toString() {
        return nombre + " (id=" + id + (ocupado ? ", ocupado)" : ", libre)");
    }
}
