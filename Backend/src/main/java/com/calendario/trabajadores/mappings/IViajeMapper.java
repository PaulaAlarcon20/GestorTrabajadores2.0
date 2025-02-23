package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.UsuarioViaje;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.ViajeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IViajeMapper {
    @Mapping(target = "idVehiculo", source = "vehiculo.id") // Only sets ID
    @Mapping(target = "idConductor", source = "conductor.id")
    @Mapping(source = "fecha", target = "fechaSalida")
    @Mapping(source = "hora", target = "horaSalida")
    CrearEditarViajeResponse viajeToCrearEditarViajeResponse(Viaje viajeGuardado);

    @Mapping(source = "idVehiculo", target = "vehiculo.id") // Only sets ID
    @Mapping(source = "idConductor", target = "conductor.id") // Only sets ID
    @Mapping(source = "fechaSalida", target = "fecha")
    @Mapping(source = "horaSalida", target = "hora")
    @Mapping(target = "usuarioViajes", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "creadoPor", ignore = true)
    @Mapping(target = "modificadoPor", ignore = true)
    Viaje crearViajeRequestToViaje(CrearViajeRequest request);

    @Mapping(target = "pasajeros", source = "usuarioViajes")
    ViajeResponse viajeToViajeResponse(Viaje viajedb);

    List<ViajeResponse> viajesToViajeResponses(List<Viaje> viajes);

    default List<String> mapUsuarioViajesToNombres(List<UsuarioViaje> usuarioViajes) {
        return usuarioViajes.stream()
                .map(usuarioViaje -> usuarioViaje.getUsuario().getNombre())
                .collect(Collectors.toList());
    }

}
