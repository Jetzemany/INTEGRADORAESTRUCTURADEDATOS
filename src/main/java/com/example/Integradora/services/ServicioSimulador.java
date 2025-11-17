package com.example.Integradora.services;

import com.example.Integradora.models.Llamada;
import com.example.Integradora.models.Operador;
import com.example.Integradora.models.TipoProblema;
import com.example.Integradora.structures.ColaPersonalizada;
import com.example.Integradora.structures.ListaPersonalizada;
import com.example.Integradora.structures.PilaPersonalizada;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServicioSimulador {

    private final ListaPersonalizada<Operador> operadores = new ListaPersonalizada<>();
    private final ColaPersonalizada<Llamada> colaEspera = new ColaPersonalizada<>();
    private final PilaPersonalizada<Llamada> historial = new PilaPersonalizada<>();

    public ServicioSimulador() {
        // Operadores iniciales (ejemplo)
        operadores.agregar(new Operador("Ana"));
        operadores.agregar(new Operador("Luis"));
        operadores.agregar(new Operador("Marta"));
    }

    //Crea y encola una llamada (cliente por nombre, tipo)
    public Llamada crearLlamada(String nombreCliente, TipoProblema tipo) {
        Llamada l = new Llamada(nombreCliente, tipo);
        colaEspera.encolar(l);
        return l;
    }

    //Intenta atender la siguiente llamada en la cola.
    //Si hay un operador libre, lo asigna (marca ocupado), apila la llamada en historial y devuelve el mensaje.
    // El operador queda ocupado hasta que se llame liberarOperador().

    public Optional<String> atenderSiguiente() {
        if (colaEspera.estaVacia()) return Optional.empty();

        Llamada llamada = colaEspera.desencolar();

        for (int i = 0; i < operadores.tamanio(); i++) {
            Operador op = operadores.obtener(i);

            if (!op.estaOcupado()) {
                op.setOcupado(true);         // operador ahora está ocupando (simulación)
                llamada.setAtendida(true);   // marca llamada como atendida
                historial.apilar(llamada);   // historial LIFO
                return Optional.of("Operador " + op.getNombre() + " (id=" + op.getId() + ") atendió: " + llamada);
            }
        }

        // Ningún operador libre se regresa la llamada al final de la cola
        colaEspera.encolar(llamada);
        return Optional.of("No hay operadores libres. La llamada regresó a la cola.");
    }

    //Libera un operador por ID (simula que terminó su atención).
    public boolean liberarOperador(int id) {
        for (int i = 0; i < operadores.tamanio(); i++) {
            Operador op = operadores.obtener(i);
            if (op.getId() == id) {
                op.setOcupado(false);
                return true;
            }
        }
        return false;
    }

    //Agrega un nuevo operador al sistema.

    public Operador agregarOperador(String nombre) {
        Operador op = new Operador(nombre);
        operadores.agregar(op);
        return op;
    }

    public Llamada registrarLlamadaAleatoria() {
        String[] nombres = {"Carlos", "María", "Pedro", "Lucía", "Jorge", "Sofía"};
        TipoProblema[] tipos = TipoProblema.values();

        String nombre = nombres[(int) (Math.random() * nombres.length)];
        TipoProblema tipo = tipos[(int) (Math.random() * tipos.length)];

        return crearLlamada(nombre, tipo);
    }

    // Métodos para mostrar/usar en la interfaz
    public String verOperadores() { return operadores.toString(); }
    public String verCola() { return colaEspera.toString(); }
    public String verHistorial() { return historial.toString(); }

    //Retorna un mapa con estado para que el controlador lo devuelva como JSON.

    public Map<String, String> obtenerEstadoMapa() {
        Map<String, String> mapa = new HashMap<>();
        mapa.put("operadores", operadores.toString());
        mapa.put("cola", colaEspera.toString());
        mapa.put("historial", historial.toString());
        return mapa;
    }

    public boolean eliminarOperadorPorId(int id) {
        for (int i = 0; i < operadores.tamanio(); i++) {
            Operador op = operadores.obtener(i);
            if (op.getId() == id) {
                operadores.eliminarEn(i);
                return true;
            }
        }
        return false;
    }
}
