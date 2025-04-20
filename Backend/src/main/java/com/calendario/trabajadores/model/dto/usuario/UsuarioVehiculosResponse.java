package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioVehiculosResponse{

    private Long id;
    private String nombre;
    @NotNull
    @NotEmpty
    private String apellido1;
    private String apellido2;
    private String email;
    private List<VehiculoDTO> vehiculos;
    private Boolean activo;


}
