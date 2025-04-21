package com.calendario.trabajadores.model.dto.viaje;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CrearViajeRequest {
    private Long idVehiculo;
    private Long idConductor;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    //Integer y no int para poder ser null
    private Integer plazas;
    //activo se podría omitir si siempre va a ser true por defecto
    //ya que no tiene sentido que el usuario diga si es activo o no al crear.
    //private Boolean activo;
}
