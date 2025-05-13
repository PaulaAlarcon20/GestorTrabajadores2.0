package com.calendario.trabajadores.model.database;

import java.sql.Date;

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
	private int Id;

	@ManyToOne
	@JoinColumn(name = "TrabajadorSolicitanteID", nullable=false, unique=true)
	private EntityUsuario TrabajadorSolicitante;
	
	@ManyToOne
	@JoinColumn(name = "TrabajadorAceptanteID", nullable=true, unique=true)
	private EntityUsuario TrabajadorAceptante;

	@ManyToOne
    @JoinColumn(name = "IdJornada", insertable = false, updatable = false) 
	private Turno jornadaID;

	@Column(name = "FechaSolicitada", nullable=false, unique=false, length=30)
	private Date FechaSolicitada;

	@Column(name = "FechaSolicitud", nullable=false, unique=false, length=30)
	private Date FechaSolicitud;

	@Column(name = "FechaCambio", nullable=true, unique=false, length=30)
	private Date FechaCambio;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "EstadoCambio", nullable=false, unique=false, length=30)
	private PeticionTurno EstadoCambio;

	@Column(name = "Activo", nullable=false, unique=false)
	private Boolean Activo;


	public CambioTurno() {
		
	}

}
