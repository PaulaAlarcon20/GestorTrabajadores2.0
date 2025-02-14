package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class UsuarioDTO {

    public Long id;
    public String nombre;
    public String apellido1;
    public String apellido2;
    public String email;
    public List<VehiculoDTO> vehiculos;
    public boolean activo;

    public UsuarioDTO(Long id, String nombre, String apellido1, String apellido2, String email, List<VehiculoDTO> vehiculos, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.vehiculos = vehiculos;
        this.activo = activo;
    }
}
