package com.calendario.trabajadores.model.dto.viaje;

import com.calendario.trabajadores.model.common.CamposComunes;
import com.calendario.trabajadores.model.database.EstadoViaje;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ViajeResponse {

    private Long idViaje;
    private VehiculoDTO vehiculo = new VehiculoDTO();
    private UsuarioResponse conductor = new UsuarioResponse();
    private List<UsuarioResponse> pasajeros = new ArrayList<>();
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    //Integer y no int para poder ser null
    private Integer plazas;
    private Boolean activo;
    private EstadoViaje estado;
}
