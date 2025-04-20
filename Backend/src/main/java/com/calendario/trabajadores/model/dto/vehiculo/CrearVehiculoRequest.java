package com.calendario.trabajadores.model.dto.vehiculo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//CrearVehiculoRequest no necesita el id del vehiculo porque se autogenera
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearVehiculoRequest {
    private String modeloCoche;
    @NotBlank(message = "La matrícula es un campo obligatorio")
    private String matricula;
    private Integer plazas;
    private Long idUsuario;
    private Boolean activo = true;
}
