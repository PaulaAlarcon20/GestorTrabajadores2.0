package com.calendario.trabajadores.entity.usuario;

import com.calendario.trabajadores.model.database.Puesto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trabajadorsanitario")
public class EntityUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "Nombre", nullable=false, unique=false, length=30)
	private String nombre;
	
	@Column(name = "Apellido", nullable=false, unique=false, length=30)
	private String apellido;
	
	@Column(name = "Email", nullable=false, unique=true, length=30)
	private String email;
	
	@Column(name = "Contrasena", nullable=false, unique=false, length=30)
	private String contrasena;
	
	@Column(name = "Telefono", nullable=false, unique=false, length=30)
	private String telefono;
	
	@Column(name = "CentroTrabajo", nullable=false, unique=false, length=30)
	private String centroTrabajo;
	
	@Column(name = "Puesto", nullable=false, unique=false, length=30)
	@Enumerated(EnumType.STRING)
	private Puesto puesto;
	
	
	@Column(name = "JornadaID", nullable=false, unique=false, length=30)
	private int jornadaID;
	
	@Column(name = "Localidad", nullable=false, unique=false, length=30)
	private String localidad;
	
	@Column(name = "PreferenciasHorarias", nullable = false, unique= false, length = 1500)
	private String preferenciasHorarias;
	
	@Column(name = "DisponibilidadHorasExtras", nullable = false, unique= false, length = 30, columnDefinition = "TINYINT(1)")
	private Boolean disponibilidadHorasExtras;
	
	@Column(name = "inicio_sesion", nullable = false, unique= false, length = 30)
	private Boolean inicioSesion;

	public EntityUsuario() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return contrasena;
	}

	public void setPassword(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCentroTrabajo() {
		return centroTrabajo;
	}

	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public int getJornadaID() {
		return jornadaID;
	}

	public void setJornadaID(int jornadaID) {
		this.jornadaID = jornadaID;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPreferenciasHorarias() {
		return preferenciasHorarias;
	}

	public void setPreferenciasHorarias(String preferenciasHorarias) {
		this.preferenciasHorarias = preferenciasHorarias;
	}

	public Boolean getDisponibilidadHorasExtras() {
		return disponibilidadHorasExtras;
	}

	public void setDisponibilidadHorasExtras(Boolean disponibilidadHorasExtras) {
		this.disponibilidadHorasExtras = disponibilidadHorasExtras;
	}

	public Boolean getInicioSesion() {
		return inicioSesion;
	}

	public void setInicioSesion(Boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	
	
	
	
	
	
	
	
	


}
