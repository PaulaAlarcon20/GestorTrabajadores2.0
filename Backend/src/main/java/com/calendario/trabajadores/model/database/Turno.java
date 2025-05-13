package com.calendario.trabajadores.model.database;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jornada")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Identificador de la tabla
    @Column(name = "IdJornada")
    private int id;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaCreacion")
    private Date fechaCreacion;
    @Column(name = "HoraInicio")
    private LocalTime horaInicio;
    @Column(name = "HoraFin")
    private LocalTime horaFin;
    @Column(name = "Activo")
    private Boolean activo;

    @OneToMany(mappedBy = "jornadaID")
    private List<EntityUsuario> lUsuarios;

    @OneToMany(mappedBy = "jornadaID")
    private List<CambioTurno> lCambioTurnos;

    public Turno() {
		
	}
}

