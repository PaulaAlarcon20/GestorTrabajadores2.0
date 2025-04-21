package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.UsuarioViaje;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.viaje.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Mapper(componentModel = "spring")
public interface IViajeMapper {

    @Mapping(target = "idVehiculo", source = "vehiculo.id")
    @Mapping(target = "idConductor", source = "conductor.id")
    @Mapping(source = "fechaSalida", target = "fechaSalida")
    @Mapping(source = "horaSalida", target = "horaSalida")
    @Mapping(target = "estado", source = "estado")
    CrearEditarViajeResponse viajeToCrearEditarViajeResponse(Viaje viajeGuardado);

    @Mapping(source = "idVehiculo", target = "vehiculo.id")
    @Mapping(source = "idConductor", target = "conductor.id")
    @Mapping(source = "fechaSalida", target = "fechaSalida")
    @Mapping(source = "horaSalida", target = "horaSalida")
    @Mapping(target = "usuarioViajes", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaModificacion", ignore = true)
    @Mapping(target = "creadoPor", ignore = true)
    @Mapping(target = "modificadoPor", ignore = true)
    Viaje crearViajeRequestToViaje(CrearViajeRequest request);


    @Mapping(target = "pasajeros", source = "usuarioViajes", qualifiedByName = "mapPasajeros")
    ViajeResponse viajeToViajeResponse(Viaje viajedb);

    @Named("mapPasajeros")
    default List<UsuarioResponse> mapPasajeros(List<UsuarioViaje> usuarioViajes) {
        if (usuarioViajes == null) return new ArrayList<>();
        return usuarioViajes.stream()
                .map(uv -> usuarioToUsuarioResponse(uv.getUsuario()))
                .collect(Collectors.toList());
    }

    @Mapping(target = "vehiculo.id", source = "idVehiculo")
    @Mapping(target = "conductor.id", source = "idConductor")
    void updateViajeFromEditarRequest(EditarViajeRequest request, @MappingTarget Viaje viaje);


    @Mapping(target = "id", source = "id")
    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "fechaSalida", source = "fechaSalida")
    @Mapping(target = "horaSalida", source = "horaSalida")
    @Mapping(target = "vehiculoId", source = "vehiculo.id")
    @Mapping(target = "conductorId", source = "conductor.id")
    ViajeDTO viajeToViajeDTO(Viaje viaje);


}

