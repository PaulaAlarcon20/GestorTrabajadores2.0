package com.calendario.trabajadores.model.dto.cambioTurno;

import com.calendario.trabajadores.model.database.PeticionTurno;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class EditarCambioTurnoRequest {
    private Integer id;
    private Date horaInicio;
    private Date horaFin;
    private PeticionTurno peticionTurno;
    private String notasPeticion;
    private Boolean activo;
}
