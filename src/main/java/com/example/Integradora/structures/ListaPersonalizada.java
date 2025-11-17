package com.example.Integradora.structures;

import java.util.Arrays;

/*
 * Lista dinámica simple implementada desde cero.
 * Tiene gregar, eliminar, obtener.
 */
public class ListaPersonalizada<E> {
    private Object[] datos;
    private int tamanio;

    public ListaPersonalizada() { this(10); }
    public ListaPersonalizada(int capacidadInicial) {
        if (capacidadInicial <= 0) capacidadInicial = 10;
        datos = new Object[capacidadInicial];
        tamanio = 0;
    }

    public void agregar(E elemento) {
        asegurarCapacidad();
        datos[tamanio++] = elemento;
    }

    public boolean eliminar(E elemento) {
        int idx = indiceDe(elemento);
        if (idx == -1) return false;
        eliminarEn(idx);
        return true;
    }

    public E eliminarEn(int indice) {
        verificarRango(indice);
        @SuppressWarnings("unchecked") E antiguo = (E) datos[indice];
        int mover = tamanio - indice - 1;
        if (mover > 0) System.arraycopy(datos, indice + 1, datos, indice, mover);
        datos[--tamanio] = null;
        return antiguo;
    }

    public E obtener(int indice) {
        verificarRango(indice);
        @SuppressWarnings("unchecked") E e = (E) datos[indice];
        return e;
    }

    public int tamanio() { return tamanio; }

    public int indiceDe(E elemento) {
        if (elemento == null) {
            for (int i = 0; i < tamanio; i++) if (datos[i] == null) return i;
        } else {
            for (int i = 0; i < tamanio; i++) if (elemento.equals(datos[i])) return i;
        }
        return -1;
    }

    public boolean contiene(E elemento) { return indiceDe(elemento) != -1; }

    private void asegurarCapacidad() {
        if (tamanio == datos.length) datos = Arrays.copyOf(datos, datos.length * 2);
    }

    private void verificarRango(int indice) {
        if (indice < 0 || indice >= tamanio) throw new IndexOutOfBoundsException("Indice: " + indice + " Tamaño: " + tamanio);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < tamanio; i++) {
            sb.append(datos[i]);
            if (i < tamanio - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
