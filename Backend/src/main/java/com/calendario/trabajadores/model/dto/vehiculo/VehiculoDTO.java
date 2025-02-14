package com.calendario.trabajadores.model.dto.vehiculo;

import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class VehiculoDTO {

    public Long id;
    public String modeloCoche;
    public String matricula;
    //Integer y no int para que pueda ser null
    public Integer plazas;
    public UsuarioDTO usuario;
    //Boolean y no boolean para que pueda ser null
    public Boolean activo;

    public VehiculoDTO(Long id, String modeloCoche, String matricula, boolean activo) {
        this.id = id;
        this.modeloCoche = modeloCoche;
        this.matricula = matricula;
        this.activo = activo;
    }
}
