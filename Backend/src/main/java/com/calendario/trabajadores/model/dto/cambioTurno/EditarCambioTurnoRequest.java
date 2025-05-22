package com.calendario.trabajadores.model.dto.cambioTurno;

import com.calendario.trabajadores.model.database.PeticionTurno;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarCambioTurnoRequest {
    private Integer id;
    private PeticionTurno nuevoEstado;
    private Boolean activo;
}
