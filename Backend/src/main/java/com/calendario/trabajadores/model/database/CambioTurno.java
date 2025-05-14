package com.calendario.trabajadores.model.database;

import java.util.Date;

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
	@JoinColumn(name = "TrabajadorSolicitanteID", nullable=false, unique=true)
	private EntityUsuario trabajadorSolicitante;
	
	@ManyToOne
	@JoinColumn(name = "TrabajadorAceptanteID", nullable=true, unique=true)
	private EntityUsuario trabajadorAceptante;

	@ManyToOne
    @JoinColumn(name = "IdJornada", insertable = false, updatable = false) 
	private Turno jornadaID;

	@Column(name = "FechaSolicitada", nullable=false, unique=false, length=30)
	private Date fechaSolicitada;

	@CurrentTimestamp
	@Column(name = "FechaSolicitud", nullable=false, unique=false, length=30)
	private Date fechaSolicitud;

	@Column(name = "FechaCambio", nullable=true, unique=false, length=30)
	private Date fechaCambio;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "EstadoCambio", nullable=false, unique=false, length=30)
	private PeticionTurno estadoCambio;

	@Column(name = "Activo")
	private Boolean activo = true;


	public CambioTurno() {
		
	}

}
