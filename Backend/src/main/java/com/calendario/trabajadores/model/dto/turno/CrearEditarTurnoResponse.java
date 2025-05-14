package com.calendario.trabajadores.model.dto.turno;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearEditarTurnoResponse {
    private int id;
    private int idUsuario;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean activo;
}
