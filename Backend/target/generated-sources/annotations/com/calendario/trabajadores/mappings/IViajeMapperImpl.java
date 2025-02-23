package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.UsuarioViaje;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.database.Viaje;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.model.dto.viaje.CrearEditarViajeResponse;
import com.calendario.trabajadores.model.dto.viaje.CrearViajeRequest;
import com.calendario.trabajadores.model.dto.viaje.ViajeResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T14:01:35+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class IViajeMapperImpl implements IViajeMapper {

    @Override
    public CrearEditarViajeResponse viajeToCrearEditarViajeResponse(Viaje viajeGuardado) {
        if ( viajeGuardado == null ) {
            return null;
        }

        CrearEditarViajeResponse crearEditarViajeResponse = new CrearEditarViajeResponse();

        crearEditarViajeResponse.setIdVehiculo( viajeGuardadoVehiculoId( viajeGuardado ) );
        crearEditarViajeResponse.setIdConductor( viajeGuardadoConductorId( viajeGuardado ) );
        crearEditarViajeResponse.setFechaSalida( viajeGuardado.getFecha() );
        crearEditarViajeResponse.setHoraSalida( viajeGuardado.getHora() );
        crearEditarViajeResponse.setOrigen( viajeGuardado.getOrigen() );
        crearEditarViajeResponse.setDestino( viajeGuardado.getDestino() );
        crearEditarViajeResponse.setPlazas( viajeGuardado.getPlazas() );
        crearEditarViajeResponse.setFechaCreacion( viajeGuardado.getFechaCreacion() );
        crearEditarViajeResponse.setFechaModificacion( viajeGuardado.getFechaModificacion() );

        return crearEditarViajeResponse;
    }

    @Override
    public Viaje crearViajeRequestToViaje(CrearViajeRequest request) {
        if ( request == null ) {
            return null;
        }

        Viaje viaje = new Viaje();

        viaje.setVehiculo( crearViajeRequestToVehiculo( request ) );
        viaje.setConductor( crearViajeRequestToUsuario( request ) );
        viaje.setFecha( request.getFechaSalida() );
        viaje.setHora( request.getHoraSalida() );
        viaje.setOrigen( request.getOrigen() );
        viaje.setDestino( request.getDestino() );
        if ( request.getPlazas() != null ) {
            viaje.setPlazas( request.getPlazas() );
        }

        return viaje;
    }

    @Override
    public ViajeResponse viajeToViajeResponse(Viaje viajedb) {
        if ( viajedb == null ) {
            return null;
        }

        ViajeResponse viajeResponse = new ViajeResponse();

        viajeResponse.setPasajeros( usuarioViajeListToUsuarioResponseList( viajedb.getUsuarioViajes() ) );
        viajeResponse.setFechaCreacion( viajedb.getFechaCreacion() );
        viajeResponse.setFechaModificacion( viajedb.getFechaModificacion() );
        viajeResponse.setCreadoPor( viajedb.getCreadoPor() );
        viajeResponse.setModificadoPor( viajedb.getModificadoPor() );
        viajeResponse.setVehiculo( vehiculoToVehiculoDTO( viajedb.getVehiculo() ) );
        viajeResponse.setConductor( usuarioToUsuarioResponse( viajedb.getConductor() ) );
        viajeResponse.setOrigen( viajedb.getOrigen() );
        viajeResponse.setDestino( viajedb.getDestino() );
        viajeResponse.setFecha( viajedb.getFecha() );
        viajeResponse.setHora( viajedb.getHora() );
        viajeResponse.setPlazas( viajedb.getPlazas() );

        return viajeResponse;
    }

    @Override
    public List<ViajeResponse> viajesToViajeResponses(List<Viaje> viajes) {
        if ( viajes == null ) {
            return null;
        }

        List<ViajeResponse> list = new ArrayList<ViajeResponse>( viajes.size() );
        for ( Viaje viaje : viajes ) {
            list.add( viajeToViajeResponse( viaje ) );
        }

        return list;
    }

    private Long viajeGuardadoVehiculoId(Viaje viaje) {
        Vehiculo vehiculo = viaje.getVehiculo();
        if ( vehiculo == null ) {
            return null;
        }
        return vehiculo.getId();
    }

    private Long viajeGuardadoConductorId(Viaje viaje) {
        Usuario conductor = viaje.getConductor();
        if ( conductor == null ) {
            return null;
        }
        return conductor.getId();
    }

    protected Vehiculo crearViajeRequestToVehiculo(CrearViajeRequest crearViajeRequest) {
        if ( crearViajeRequest == null ) {
            return null;
        }

        Vehiculo vehiculo = new Vehiculo();

        vehiculo.setId( crearViajeRequest.getIdVehiculo() );

        return vehiculo;
    }

    protected Usuario crearViajeRequestToUsuario(CrearViajeRequest crearViajeRequest) {
        if ( crearViajeRequest == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( crearViajeRequest.getIdConductor() );

        return usuario;
    }

    protected UsuarioResponse usuarioViajeToUsuarioResponse(UsuarioViaje usuarioViaje) {
        if ( usuarioViaje == null ) {
            return null;
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioResponse.setId( usuarioViaje.getId() );

        return usuarioResponse;
    }

    protected List<UsuarioResponse> usuarioViajeListToUsuarioResponseList(List<UsuarioViaje> list) {
        if ( list == null ) {
            return null;
        }

        List<UsuarioResponse> list1 = new ArrayList<UsuarioResponse>( list.size() );
        for ( UsuarioViaje usuarioViaje : list ) {
            list1.add( usuarioViajeToUsuarioResponse( usuarioViaje ) );
        }

        return list1;
    }

    protected VehiculoDTO vehiculoToVehiculoDTO(Vehiculo vehiculo) {
        if ( vehiculo == null ) {
            return null;
        }

        VehiculoDTO vehiculoDTO = new VehiculoDTO();

        vehiculoDTO.setFechaCreacion( vehiculo.getFechaCreacion() );
        vehiculoDTO.setFechaModificacion( vehiculo.getFechaModificacion() );
        vehiculoDTO.setCreadoPor( vehiculo.getCreadoPor() );
        vehiculoDTO.setModificadoPor( vehiculo.getModificadoPor() );
        vehiculoDTO.setId( vehiculo.getId() );
        vehiculoDTO.setModeloCoche( vehiculo.getModeloCoche() );
        vehiculoDTO.setMatricula( vehiculo.getMatricula() );
        vehiculoDTO.setPlazas( vehiculo.getPlazas() );
        vehiculoDTO.setActivo( vehiculo.getActivo() );

        return vehiculoDTO;
    }

    protected UsuarioResponse usuarioToUsuarioResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioResponse.setFechaCreacion( usuario.getFechaCreacion() );
        usuarioResponse.setFechaModificacion( usuario.getFechaModificacion() );
        usuarioResponse.setCreadoPor( usuario.getCreadoPor() );
        usuarioResponse.setModificadoPor( usuario.getModificadoPor() );
        usuarioResponse.setId( usuario.getId() );
        usuarioResponse.setNombre( usuario.getNombre() );
        usuarioResponse.setApellido1( usuario.getApellido1() );
        usuarioResponse.setApellido2( usuario.getApellido2() );
        usuarioResponse.setEmail( usuario.getEmail() );
        usuarioResponse.setTelefono( usuario.getTelefono() );
        usuarioResponse.setCentroTrabajo( usuario.getCentroTrabajo() );
        usuarioResponse.setPuesto( usuario.getPuesto() );
        usuarioResponse.setLocalidad( usuario.getLocalidad() );
        usuarioResponse.setPreferenciasHorarias( usuario.getPreferenciasHorarias() );
        usuarioResponse.setDisponibilidadHorasExtras( usuario.getDisponibilidadHorasExtras() );
        usuarioResponse.setRol( usuario.getRol() );
        usuarioResponse.setActivo( usuario.getActivo() );

        return usuarioResponse;
    }
}
