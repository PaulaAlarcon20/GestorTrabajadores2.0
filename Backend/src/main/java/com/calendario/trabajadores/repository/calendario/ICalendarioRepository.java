package com.calendario.trabajadores.repository.calendario;

import com.calendario.trabajadores.model.database.EventoCalendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICalendarioRepository
        extends JpaRepository<EventoCalendario, Long>,
                CustomCalendarioRepository {
    List<EventoCalendario> findByDiaCompleto(boolean diaCompleto);
    List<EventoCalendario> findByTituloContainingIgnoreCase(String palabraClave);
}