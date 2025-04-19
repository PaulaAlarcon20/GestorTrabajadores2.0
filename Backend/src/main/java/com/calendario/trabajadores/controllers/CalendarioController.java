package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.database.EventoCalendario;
import com.calendario.trabajadores.services.calendario.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos-calendario")
@CrossOrigin(origins = "*")
public class CalendarioController {
    private final CalendarioService service;

    public CalendarioController(CalendarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EventoCalendario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoCalendario> obtener(@PathVariable Long id) {
        return service.buscarPorId(id)
                      .map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventoCalendario> crear(@RequestBody EventoCalendario evento) {
        EventoCalendario nuevo = service.guardar(evento);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoCalendario> actualizar(
            @PathVariable Long id,
            @RequestBody EventoCalendario evento
    ) {
        return service.buscarPorId(id)
                      .map(existente -> {
                          existente.setTitulo(evento.getTitulo());
                          existente.setDescripcion(evento.getDescripcion());
                          existente.setInicio(evento.getInicio());
                          existente.setFin(evento.getFin());
                          existente.setDiaCompleto(evento.isDiaCompleto());
                          EventoCalendario actualizado = service.guardar(existente);
                          return ResponseEntity.ok(actualizado);
                      })
                      .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
