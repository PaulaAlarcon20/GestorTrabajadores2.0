package com.calendario.trabajadores.model.dto.vehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditarVehiculoRequest {
    private Long id;
    private String modeloCoche;
    private String matricula;
    private Integer plazas;
    private Long idUsuario;
    private Boolean activo = true;
}
