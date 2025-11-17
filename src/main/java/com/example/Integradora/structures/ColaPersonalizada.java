package com.example.Integradora.structures;

/*
 * Cola FIFO
 */
public class ColaPersonalizada<E> {
    private Object[] datos;
    private int cabeza;
    private int cola;
    private int tamanio;

    public ColaPersonalizada() { this(10); }
    public ColaPersonalizada(int capacidad) { datos = new Object[Math.max(1, capacidad)]; cabeza = 0; cola = 0; tamanio = 0; }

    public void encolar(E elem) {
        if (tamanio == datos.length) crecer();
        datos[cola] = elem;
        cola = (cola + 1) % datos.length;
        tamanio++;
    }

    @SuppressWarnings("unchecked")
    public E desencolar() {
        if (estaVacia()) return null;
        E val = (E) datos[cabeza];
        datos[cabeza] = null;
        cabeza = (cabeza + 1) % datos.length;
        tamanio--;
        return val;
    }

    @SuppressWarnings("unchecked")
    public E frente() {
        if (estaVacia()) return null;
        return (E) datos[cabeza];
    }

    public boolean estaVacia() { return tamanio == 0; }
    public int tamanio() { return tamanio; }

    private void crecer() {
        Object[] nuevo = new Object[datos.length * 2];
        for (int i = 0; i < tamanio; i++) {
            nuevo[i] = datos[(cabeza + i) % datos.length];
        }
        datos = nuevo;
        cabeza = 0;
        cola = tamanio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cola[");
        for (int i = 0; i < tamanio; i++) {
            sb.append(datos[(cabeza + i) % datos.length]);
            if (i < tamanio - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
