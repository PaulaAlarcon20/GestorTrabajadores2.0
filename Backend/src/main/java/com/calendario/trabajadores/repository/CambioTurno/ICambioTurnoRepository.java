package com.calendario.trabajadores.repository.CambioTurno;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;
import com.calendario.trabajadores.model.database.CambioTurno;
import com.calendario.trabajadores.model.database.PeticionTurno;

/*
 * JpaRepository hereda todos los métodos necesarios para realizar el CRUD
 * */
@Repository
public interface ICambioTurnoRepository extends JpaRepository<CambioTurno, Integer> {
	
    List<CambioTurno> findCambioTurnoByActivo(boolean activo);

    List<CambioTurno> findCambioTurnoByTrabajadorSolicitante(EntityUsuario trabajador);
    List<CambioTurno> findCambioTurnoByTrabajadorSolicitante(Optional<EntityUsuario> trabajador);
    

    List<CambioTurno> findCambioTurnoByTrabajadorSolicitanteAndEstadoCambio(EntityUsuario trabajador, PeticionTurno estado);
    List<CambioTurno> findCambioTurnoByTrabajadorSolicitanteAndEstadoCambio(Optional<EntityUsuario> trabajador, PeticionTurno estado);

    List<CambioTurno> findCambioTurnoByTrabajadorSolicitanteNotAndEstadoCambio(Optional<EntityUsuario> trabajador, PeticionTurno estado);

    List<CambioTurno> findCambioTurnoByTrabajadorAceptanteAndEstadoCambio(EntityUsuario trabajador, PeticionTurno estado);
}