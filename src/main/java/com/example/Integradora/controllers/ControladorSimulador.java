package com.example.Integradora.controllers;

import com.example.Integradora.models.Llamada;
import com.example.Integradora.models.Operador;
import com.example.Integradora.models.TipoProblema;
import com.example.Integradora.services.ServicioSimulador;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ControladorSimulador {

    private final ServicioSimulador servicio;

    public ControladorSimulador(ServicioSimulador servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/crear-llamada")
    public ResponseEntity<?> crearLlamada(@RequestParam String cliente, @RequestParam String problema) {
        TipoProblema tp;
        try {
            tp = TipoProblema.valueOf(problema.trim().toUpperCase());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Tipo de problema inválido");
        }
        Llamada l = servicio.crearLlamada(cliente, tp);
        return ResponseEntity.ok("Llamada creada: " + l);
    }

    @PostMapping("/crear-aleatoria")
    public ResponseEntity<?> crearAleatoria() {
        Llamada l = servicio.registrarLlamadaAleatoria();
        return ResponseEntity.ok("Llamada aleatoria creada: " + l);
    }

    @PostMapping("/atender")
    public ResponseEntity<?> atender() {
        Optional<String> res = servicio.atenderSiguiente();
        return res.map(ResponseEntity::ok).orElse(ResponseEntity.ok("No hay llamadas en espera."));
    }

    @PostMapping("/agregar-operador")
    public ResponseEntity<?> agregarOperador(@RequestParam String nombre) {
        Operador op = servicio.agregarOperador(nombre);
        return ResponseEntity.ok("Operador agregado: " + op);
    }

    @PostMapping("/liberar-operador")
    public ResponseEntity<?> liberarOperador(@RequestParam int id) {
        boolean ok = servicio.liberarOperador(id);
        if (ok) return ResponseEntity.ok("Operador liberado: " + id);
        else return ResponseEntity.badRequest().body("Operador no encontrado: " + id);
    }

    @GetMapping("/estado")
    public ResponseEntity<Map<String, String>> estado() {
        return ResponseEntity.ok(servicio.obtenerEstadoMapa());
    }
}
