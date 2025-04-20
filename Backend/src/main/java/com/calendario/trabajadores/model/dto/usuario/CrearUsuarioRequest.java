package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.database.Puesto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearUsuarioRequest {
    private String nombre;
    @NotNull
    @NotEmpty
    private String apellido1;
    private String apellido2;
    private String email;
    // Password solo en request porque no se devuelve en response
    private String password;
    private String telefono;
    private String centroTrabajo;
    private Puesto puesto;
    private String localidad;
    private String preferenciasHorarias;
    private Boolean disponibilidadHorasExtras;
    private String rol;
    private Boolean activo;

}

