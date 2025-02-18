package com.calendario.trabajadores.model.database;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Identificador de la tabla
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) //Revisar lo de usuario id**
    public Usuario usuario;
    /*@ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;*/
    @Column(nullable = false)
    private ZonedDateTime horaInicio;

    @Column(nullable = false)
    private ZonedDateTime horaFin;

    //Estado del turno
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public EstadoTurno estadoTurno;
    //Estado de la petición de cambio de turno
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public PeticionTurno peticionTurno;
    //Puede ir una nota asociada a la petición de cambio de turno
    @Column(columnDefinition = "TEXT") // text si es largo el texto asociado?¿?¿
    public String notasPeticion;
    // Constructor para asignar zona horaria por defecto
    public Turno() {
        this.horaInicio = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        this.horaFin = ZonedDateTime.now(ZoneId.of("Europe/Madrid")).plusHours(8); // Ejemplo: turno de 8 horas
    }
}

