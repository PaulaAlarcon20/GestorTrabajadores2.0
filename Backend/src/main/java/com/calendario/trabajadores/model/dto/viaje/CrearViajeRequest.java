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

public class CrearViajeRequest {
    public Long idVehiculo;

    public Long idConductor;

    public String origen;
    public String destino;

    public LocalDate fechaSalida;

    public LocalTime horaSalida;
    //Integer y no int para poder ser null
    public Integer plazas;
    public Boolean activo;  //**** Inluyo activo? TODO:Supuestamente no si se crea activo por defecto
    //algo mas es activo por defecto? como usuario o vehiculo??
}
