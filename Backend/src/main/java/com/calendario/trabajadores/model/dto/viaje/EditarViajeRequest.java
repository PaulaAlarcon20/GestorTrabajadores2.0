package com.calendario.trabajadores.model.dto.viaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EditarViajeRequest {
    private Long idVehiculo;

    private Long idConductor;

    private String origen;
    private String destino;

    private LocalDate fechaSalida;

    private LocalTime horaSalida;
    //Integer y no int para poder ser null
    private Integer plazas;
    //public Boolean activo;  //**** Inluyo activo? TODO:Pregunta
}
