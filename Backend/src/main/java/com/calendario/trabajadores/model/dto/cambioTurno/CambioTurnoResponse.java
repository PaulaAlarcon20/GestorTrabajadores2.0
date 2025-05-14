package com.calendario.trabajadores.model.dto.cambioTurno;

import com.calendario.trabajadores.model.database.PeticionTurno;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CambioTurnoResponse {
    private int id;
    private int idUsuario;
    private PeticionTurno peticionTurno;
}
