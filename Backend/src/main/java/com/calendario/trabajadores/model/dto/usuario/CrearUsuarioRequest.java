package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.database.Puesto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CrearUsuarioRequest {
    private String nombre;
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
    private Boolean activo;  //**** TODO:Revisar

}

