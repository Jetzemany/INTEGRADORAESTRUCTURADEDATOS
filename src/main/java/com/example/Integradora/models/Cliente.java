package com.example.Integradora.models;

/*
 * Cliente simple: id auto-incremental y nombre (o teléfono si lo quieres).
 * Versión ligera para la integradora.
 */
public class Cliente {
    private static int CONT = 1;
    private final int id;
    private final String nombre;
    private final String telefono;
    public Cliente(String nombre, String telefono) {
        this.id = CONT++;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }

    @Override
    public String toString() {
        return nombre + (telefono != null ? " (" + telefono + ")" : "");
    }
}
