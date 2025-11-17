package com.example.Integradora.models;

/*
 * Llamada simple: id auto, nombre del cliente y tipo de problema.
 * Diseñada para integrarse con las estructuras (cola y pila).
 */
public class Llamada {
    private static int CONTADOR = 1;

    private final int id;
    private final String clienteNombre;
    private final TipoProblema tipoProblema;
    private boolean atendida = false;

    public Llamada(String clienteNombre, TipoProblema tipoProblema) {
        this.id = CONTADOR++;
        this.clienteNombre = clienteNombre;
        this.tipoProblema = tipoProblema;
    }

    public int getId() { return id; }
    public String getClienteNombre() { return clienteNombre; }
    public TipoProblema getTipoProblema() { return tipoProblema; }

    public boolean isAtendida() { return atendida; }
    public void setAtendida(boolean atendida) { this.atendida = atendida; }

    @Override
    public String toString() {
        return "Llamada{id=" + id + ", cliente='" + clienteNombre + "', tipo=" + tipoProblema + ", atendida=" + atendida + "}";
    }
}
