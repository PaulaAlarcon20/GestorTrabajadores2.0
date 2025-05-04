package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.database.Puesto;

public class UsuarioDTO {

	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	private String telefono;
	private String centroTrabajo;
	private Puesto puesto;
	private int jornadaID;
	private String localidad;
	private String preferenciasHorarias;
	private Boolean disponibilidadHorasExtras;
	private Boolean inicioSesion;

	public UsuarioDTO(int id, String nombre, String apellido, String email, String password, String telefono,
			String centroTrabajo, Puesto puesto, int jornadaID, String localidad, String preferenciasHorarias,
			Boolean disponibilidadHorasExtras, Boolean inicioSesion) {

		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
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
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
