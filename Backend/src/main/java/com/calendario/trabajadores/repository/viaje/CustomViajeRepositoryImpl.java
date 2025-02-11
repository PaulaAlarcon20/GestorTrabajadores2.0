package com.calendario.trabajadores.repository.viaje;

import com.calendario.trabajadores.model.database.Viaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomViajeRepositoryImpl implements CustomViajeRepository {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * @param usuarioId
     * @return
     */
    @Override
    public List<Viaje> mostrarViajesConductor(Long usuarioId) {
        return null;
    }

    /**
     * @param usuarioId
     * @return
     */
    @Override
    public List<Viaje> mostrarMisViajes(Long usuarioId) {
        return null;
    }

    /**
     * @param viaje
     * @return
     */
    @Override
    public Viaje actualizarViaje(Viaje viaje) {
        return null;
    }

    /**
     * @param viajeId
     */
    @Override
    public void cancelarViaje(Long viajeId) {

    }

    /**
     * @param viajeId
     * @param usuarioId
     */
    @Override
    public void agregarUsuarioViaje(Long viajeId, Long usuarioId) {

    }

    /**
     * @param viajeId
     * @param usuarioId
     */
    @Override
    public void eliminarUsuarioViaje(Long viajeId, Long usuarioId) {

    }
}
