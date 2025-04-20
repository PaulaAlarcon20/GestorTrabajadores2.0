package com.calendario.trabajadores.model.dto.usuario;

import com.calendario.trabajadores.model.common.CamposComunes;
import com.calendario.trabajadores.model.database.Puesto;
import com.calendario.trabajadores.model.dto.viaje.ViajeDTO;
import com.calendario.trabajadores.model.dto.viaje.ViajeResponse;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse extends CamposComunes {

    private Long id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private String telefono;
    private String centroTrabajo;
    private Puesto puesto;
    private String localidad;
    private String preferenciasHorarias;
    private Boolean disponibilidadHorasExtras;
    private String rol;
    private Boolean activo;
    private List<ViajeDTO> viajes; //*F*
}
