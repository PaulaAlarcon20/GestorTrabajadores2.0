package com.calendario.trabajadores.repository.turno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendario.trabajadores.model.database.Turno;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer>, CustomTurnoRepository {

    // Metodo para obtener turnos por id de usuario y estado
    List<Turno> findByIdAndActivo(int usuarioId, Boolean estadoTurno);

    // Metodo para obtener turnos por id de usuario
    Turno findById(int usuarioId);
}
