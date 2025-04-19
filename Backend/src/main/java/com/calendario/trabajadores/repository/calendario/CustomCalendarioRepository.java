package com.calendario.trabajadores.repository.calendario;

import com.calendario.trabajadores.model.database.EventoCalendario;
import java.time.LocalDateTime;
import java.util.List;

public interface CustomCalendarioRepository {
    /**
     * Busca todos los eventos cuyo fin sea posterior al instante actual
     */
    List<EventoCalendario> buscarEventosActivos();

    /**
     * Busca eventos que comienzan dentro de un rango de fechas
     */
    List<EventoCalendario> buscarPorRango(LocalDateTime desde, LocalDateTime hasta);
}