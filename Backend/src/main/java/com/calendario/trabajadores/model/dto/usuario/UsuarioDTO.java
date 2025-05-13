package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.database.Puesto;
import com.calendario.trabajadores.model.database.Turno;

public class UsuarioDTO {

	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasena;
	private String telefono;
	private String centroTrabajo;
	private Puesto puesto;
	private Turno jornadaID;
	private String localidad;
	private String preferenciasHorarias;
	private Boolean disponibilidadHorasExtras;
	private Boolean inicioSesion;

	public UsuarioDTO(Integer id, String nombre, String apellido, String email, String contrasena, String telefono,
			String centroTrabajo, Puesto puesto, Turno jornadaID, String localidad, String preferenciasHorarias,
			Boolean disponibilidadHorasExtras, Boolean inicioSesion) {

		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasena = contrasena;
		this.telefono = telefono;
		this.centroTrabajo = centroTrabajo;
		this.puesto = puesto;
		this.jornadaID = jornadaID;
		this.localidad = localidad;
		this.preferenciasHorarias = preferenciasHorarias;
		this.disponibilidadHorasExtras = disponibilidadHorasExtras;
		this.inicioSesion = inicioSesion;

	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Turno getJornadaID() {
		return jornadaID;
	}

	public void setJornadaID(Turno jornadaID) {
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
