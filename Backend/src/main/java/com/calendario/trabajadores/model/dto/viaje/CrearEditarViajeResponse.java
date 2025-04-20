package com.calendario.trabajadores.model.dto.viaje;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearEditarViajeResponse {
    private Long id;
    private Long idVehiculo;

    private Long idConductor;

    private String origen;
    private String destino;

    private LocalDate fechaSalida;

    private LocalTime horaSalida;
    //Integer y no int para poder ser null
    private Integer plazas;
    private Boolean activo;  //**** Incluir activo?
    @CreationTimestamp
    @Column(updatable = false)
    private Date fechaCreacion;

    @UpdateTimestamp
    private Date fechaModificacion;



}
