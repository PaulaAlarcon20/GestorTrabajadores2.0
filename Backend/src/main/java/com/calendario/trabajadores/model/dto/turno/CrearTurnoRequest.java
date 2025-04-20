package com.calendario.trabajadores.model.dto.turno;

import com.calendario.trabajadores.model.database.PeticionTurno;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CrearTurnoRequest {
    private Long idUsuario;
    private Date horaInicio;
    private Date horaFin;
    private String notasPeticion;
    private PeticionTurno peticionTurno;
}
