package com.calendario.trabajadores.model.dto.cambioTurno;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CrearCambioTurnoRequest {
    private Long idUsuario;
    private Date horaInicio;
    private Date horaFin;
    private String notasPeticion;
}
