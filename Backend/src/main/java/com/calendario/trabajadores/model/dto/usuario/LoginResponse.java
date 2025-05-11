package com.calendario.trabajadores.model.dto.usuario;

/*
 * Clase DTO de LoginResponse el cual envia datos del controller al front-end
 * */
public class LoginResponse {

	
	private String email;
	private boolean inicioSesion;
	private String mensaje;

	
	
	public LoginResponse(String email, boolean inicioSesion, String mensaje) {
		this.email = email;
		this.inicioSesion = inicioSesion;
		this.mensaje = mensaje;
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getInicioSesion() {
		return inicioSesion;
	}
	public void setInicioSesion(boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
