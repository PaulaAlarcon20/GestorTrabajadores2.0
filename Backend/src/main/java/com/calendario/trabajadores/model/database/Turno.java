package com.calendario.trabajadores.model.database;

import com.calendario.trabajadores.model.common.CamposComunes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turnos")
public class Turno extends CamposComunes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Identificador de la tabla
    private Long id;
    @ManyToOne
    //Revisar usuario id**
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario = new Usuario();

    @Column(nullable = false)
    private Date horaInicio;

    @Column(nullable = false)
    private Date horaFin;

    //Estado del turno
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoTurno estadoTurno;
    //Estado de la petición de cambio de turno
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PeticionTurno peticionTurno;
    //Puede ir una nota asociada a la petición de cambio de turno
    private String notasPeticion;
    // Inicializado como activo por defecto
    private Boolean activo = true;

    //Se sustituye seccion al heredar de CamposComunes
    /*@CreationTimestamp
    @Column(updatable = false)
    private Date fechaCreacion;

    @UpdateTimestamp
    private Date fechaModificacion;

    private String creadoPor;
    private String modificadoPor;*/

}

