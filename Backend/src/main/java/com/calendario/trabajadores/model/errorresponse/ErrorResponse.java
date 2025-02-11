package com.calendario.trabajadores.model.errorresponse;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Error response model")
public class ErrorResponse {
    @Schema(description = "Error message", example = "Invalid credentials")
    private String error;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

