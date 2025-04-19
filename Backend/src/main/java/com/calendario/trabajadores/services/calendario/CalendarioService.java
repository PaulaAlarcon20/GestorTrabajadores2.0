package com.calendario.trabajadores.services.calendario;

import com.calendario.trabajadores.model.database.EventoCalendario;
import com.calendario.trabajadores.repository.calendario.ICalendarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    private final ICalendarioRepository repo;

    public CalendarioService(ICalendarioRepository repo) {
        this.repo = repo;
    }

    public List<EventoCalendario> listarTodos() {
        return repo.findAll();
    }

    public Optional<EventoCalendario> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public EventoCalendario guardar(EventoCalendario evento) {
        return repo.save(evento);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    public List<EventoCalendario> buscarActivos() {
        return repo.buscarEventosActivos();
    }

    public List<EventoCalendario> buscarPorRango(LocalDateTime desde, LocalDateTime hasta) {
        return repo.buscarPorRango(desde, hasta);
    }
}