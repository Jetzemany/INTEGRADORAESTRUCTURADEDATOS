package com.example.Integradora.structures;

/*
 * Pila LIFO implementada desde cero.
 */
public class PilaPersonalizada<E> {
    private Object[] datos;
    private int tope; // índice de inserción

    public PilaPersonalizada() { this(10); }
    public PilaPersonalizada(int capacidad) { datos = new Object[Math.max(1, capacidad)]; tope = 0; }

    public void apilar(E valor) {
        if (tope == datos.length) crecer();
        datos[tope++] = valor;
    }

    @SuppressWarnings("unchecked")
    public E desapilar() {
        if (estaVacia()) return null;
        E val = (E) datos[--tope];
        datos[tope] = null;
        return val;
    }

    @SuppressWarnings("unchecked")
    public E cima() {
        if (estaVacia()) return null;
        return (E) datos[tope - 1];
    }

    public boolean estaVacia() { return tope == 0; }
    public int tamanio() { return tope; }

    private void crecer() {
        Object[] nuevo = new Object[datos.length * 2];
        System.arraycopy(datos, 0, nuevo, 0, datos.length);
        datos = nuevo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pila[");
        for (int i = tope - 1; i >= 0; i--) {
            sb.append(datos[i]);
            if (i > 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
