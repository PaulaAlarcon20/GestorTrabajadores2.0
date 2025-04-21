package com.calendario.trabajadores.repository.viaje;

import com.calendario.trabajadores.model.database.EstadoViaje;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.UsuarioViaje;
import com.calendario.trabajadores.model.database.Viaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomViajeRepositoryImpl implements CustomViajeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Viaje> findAllViajesByEstado(EstadoViaje estado) {
        String jpql = "SELECT v FROM Viaje v WHERE v.estado = :estado";
        TypedQuery<Viaje> query = entityManager.createQuery(jpql, Viaje.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    @Override
    public List<Viaje> findViajesByUsuarioAndEstado(Long usuarioId, EstadoViaje estado) {
        String jpql = "SELECT DISTINCT v FROM Viaje v " +
                "LEFT JOIN v.usuarioViajes uv " +
                "WHERE (v.conductor.id = :usuarioId OR uv.usuario.id = :usuarioId) " +
                "AND v.estado = :estado";
        TypedQuery<Viaje> query = entityManager.createQuery(jpql, Viaje.class);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("estado", estado);
        return query.getResultList();
    }

    @Override
    public List<Viaje> mostrarViajesConductor(Long usuarioId) {
        String jpql = "SELECT v FROM Viaje v WHERE v.conductor.id = :usuarioId";
        return entityManager.createQuery(jpql, Viaje.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    public List<Viaje> mostrarMisViajes(Long usuarioId) {
        String jpql = "SELECT uv.viaje FROM UsuarioViaje uv WHERE uv.usuario.id = :usuarioId";
        return entityManager.createQuery(jpql, Viaje.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    public Viaje actualizarViaje(Viaje viaje) {
        return entityManager.merge(viaje);
    }

    @Override
    public void cancelarViaje(Long viajeId) {
        Viaje viaje = entityManager.find(Viaje.class, viajeId);
        if (viaje != null && viaje.getEstado() == EstadoViaje.DISPONIBLE) {
            viaje.setEstado(EstadoViaje.CANCELADO);
            entityManager.merge(viaje);
        }
    }

    @Override
    public void agregarUsuarioViaje(Long viajeId, Long usuarioId) {
        Viaje viaje = entityManager.find(Viaje.class, viajeId);
        Usuario usuario = entityManager.find(Usuario.class, usuarioId);

        if (viaje != null && usuario != null) {
            UsuarioViaje uv = new UsuarioViaje();
            uv.setViaje(viaje);
            uv.setUsuario(usuario);
            entityManager.persist(uv);
        }
    }

    @Override
    public void eliminarUsuarioViaje(Long viajeId, Long usuarioId) {
        String jpql = "SELECT uv FROM UsuarioViaje uv WHERE uv.viaje.id = :viajeId AND uv.usuario.id = :usuarioId";
        List<UsuarioViaje> resultado = entityManager.createQuery(jpql, UsuarioViaje.class)
                .setParameter("viajeId", viajeId)
                .setParameter("usuarioId", usuarioId)
                .getResultList();

        for (UsuarioViaje uv : resultado) {
            entityManager.remove(uv);
        }
    }
}
