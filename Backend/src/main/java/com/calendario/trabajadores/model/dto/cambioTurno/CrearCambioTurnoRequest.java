package com.calendario.trabajadores.model.dto.cambioTurno;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearCambioTurnoRequest {
    @JsonProperty("usuarioId")
    private int usuarioId;
    
    @JsonProperty("jornadaId")
    private int jornadaId;
    
    @JsonProperty("fechaSolicitada")
    private String fechaSolicitada;
}