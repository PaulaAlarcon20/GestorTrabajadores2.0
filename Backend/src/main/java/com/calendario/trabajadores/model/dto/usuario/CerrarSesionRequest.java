package com.calendario.trabajadores.model.dto.usuario;

public class CerrarSesionRequest {
	
	 private String email;
	 private boolean inicioSesion;
 
	 public CerrarSesionRequest(String email, boolean inicioSesion) {
		super();
		this.email = email;
		this.inicioSesion = inicioSesion;
	 }

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isInicioSesion() {
		return inicioSesion;
	}
	
	public void setInicioSesion(boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}
	 
	 
 
 

}


