package com.calendario.trabajadores.model.dto.turno;

import org.springframework.http.HttpStatus;

public class ErrorResponse01 {
    private String error;
    private String message;
    private String status;
    
    // Constructores, getters y setters
    public ErrorResponse01(String error, String message, HttpStatus status) {
        this.error = error;
        this.message = message;
        this.status = status.toString();
    }
}
