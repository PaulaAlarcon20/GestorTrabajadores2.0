package com.calendario.trabajadores.model.database;

import java.time.LocalDate;

import org.hibernate.annotations.CurrentTimestamp;

import com.calendario.trabajadores.entity.usuario.EntityUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cambioturno")
public class CambioTurno {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCambioTurno")
    private int id;

	@ManyToOne
	@JoinColumn(name = "TrabajadorSolicitanteID", nullable=false)
	private EntityUsuario trabajadorSolicitante;
	
	@ManyToOne
	@JoinColumn(name = "TrabajadorAceptanteID", nullable=true)
	private EntityUsuario trabajadorAceptante;

	@ManyToOne
    @JoinColumn(name = "IdJornada", updatable = false) 
	private Turno jornadaID;

	@Column(name = "FechaSolicitada", nullable=false, length=30)
	private LocalDate fechaSolicitada;

	@CurrentTimestamp
	@Column(name = "FechaSolicitud", nullable=false, length=30)
	private LocalDate fechaSolicitud = LocalDate.now();

	@Column(name = "FechaCambio", nullable=true, length=30)
	private LocalDate fechaCambio;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "EstadoCambio", nullable=false, length=30)
	private PeticionTurno estadoCambio = PeticionTurno.PENDIENTE;

	@Column(name = "Activo")
	private Boolean activo = true;


	public CambioTurno() {
		
	}

}
