package com.calendario.trabajadores.repository.calendario;

import com.calendario.trabajadores.model.database.EventoCalendario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomCalendarioRepositoryImpl implements CustomCalendarioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EventoCalendario> buscarEventosActivos() {
        String jpql = "SELECT e FROM EventoCalendario e WHERE e.fin >= :ahora";
        TypedQuery<EventoCalendario> q = em.createQuery(jpql, EventoCalendario.class);
        q.setParameter("ahora", LocalDateTime.now());
        return q.getResultList();
    }

    @Override
    public List<EventoCalendario> buscarPorRango(LocalDateTime desde, LocalDateTime hasta) {
        String jpql = "SELECT e FROM EventoCalendario e WHERE e.inicio BETWEEN :desde AND :hasta";
        TypedQuery<EventoCalendario> q = em.createQuery(jpql, EventoCalendario.class);
        q.setParameter("desde", desde);
        q.setParameter("hasta", hasta);
        return q.getResultList();
    }
}
