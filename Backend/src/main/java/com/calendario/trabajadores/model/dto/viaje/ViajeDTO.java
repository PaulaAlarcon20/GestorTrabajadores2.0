package com.calendario.trabajadores.model.dto.viaje;

import com.calendario.trabajadores.model.database.Viaje;

import java.util.Date;

public class ViajeDTO {

    public Long idVehiculo;

    public Long idConductor;

    public String origen;
    public String destino;

    public Date fechaSalida;

    public Date horaSalida;

    public int plazas;


}
