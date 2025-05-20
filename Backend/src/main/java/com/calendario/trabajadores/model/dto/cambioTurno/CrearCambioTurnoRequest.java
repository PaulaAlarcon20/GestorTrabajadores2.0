package com.calendario.trabajadores.model.dto.cambioTurno;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearCambioTurnoRequest {

    private int usuarioId;
    private int jornadaId;
    private LocalDate fechaSolicitada;

    // Constructor vacío necesario para la deserialización
    public CrearCambioTurnoRequest() {}

    // Constructor con parámetros opcional (puede ser útil para pruebas)
    public CrearCambioTurnoRequest(int usuarioId, int jornadaId, LocalDate fechaSolicitada) {
        this.usuarioId = usuarioId;
        this.jornadaId = jornadaId;
        this.fechaSolicitada = fechaSolicitada;
    }

    @Override
    public String toString() {
        return "CrearCambioTurnoRequest{" +
                "usuarioId=" + usuarioId +
                ", jornadaId=" + jornadaId +
                ", fechaSolicitada=" + fechaSolicitada +
                '}';
    }
}
