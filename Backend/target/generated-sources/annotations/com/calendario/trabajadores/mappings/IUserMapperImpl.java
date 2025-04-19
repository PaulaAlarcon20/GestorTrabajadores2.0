package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-19T13:37:19+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.12 (Microsoft)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UsuarioResponse userToCreateEditResponse(Usuario userDB) {
        if ( userDB == null ) {
            return null;
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioResponse.setFechaCreacion( userDB.getFechaCreacion() );
        usuarioResponse.setFechaModificacion( userDB.getFechaModificacion() );
        usuarioResponse.setCreadoPor( userDB.getCreadoPor() );
        usuarioResponse.setModificadoPor( userDB.getModificadoPor() );
        usuarioResponse.setId( userDB.getId() );
        usuarioResponse.setNombre( userDB.getNombre() );
        usuarioResponse.setApellido1( userDB.getApellido1() );
        usuarioResponse.setApellido2( userDB.getApellido2() );
        usuarioResponse.setEmail( userDB.getEmail() );
        usuarioResponse.setTelefono( userDB.getTelefono() );
        usuarioResponse.setCentroTrabajo( userDB.getCentroTrabajo() );
        usuarioResponse.setPuesto( userDB.getPuesto() );
        usuarioResponse.setLocalidad( userDB.getLocalidad() );
        usuarioResponse.setPreferenciasHorarias( userDB.getPreferenciasHorarias() );
        usuarioResponse.setDisponibilidadHorasExtras( userDB.getDisponibilidadHorasExtras() );
        usuarioResponse.setRol( userDB.getRol() );
        usuarioResponse.setActivo( userDB.getActivo() );

        return usuarioResponse;
    }

    @Override
    public Usuario createRequestToUser(CrearUsuarioRequest request) {
        if ( request == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setContraseña( request.getPassword() );
        usuario.setNombre( request.getNombre() );
        usuario.setApellido1( request.getApellido1() );
        usuario.setApellido2( request.getApellido2() );
        usuario.setEmail( request.getEmail() );
        usuario.setTelefono( request.getTelefono() );
        usuario.setCentroTrabajo( request.getCentroTrabajo() );
        usuario.setPuesto( request.getPuesto() );
        usuario.setLocalidad( request.getLocalidad() );
        usuario.setPreferenciasHorarias( request.getPreferenciasHorarias() );
        usuario.setDisponibilidadHorasExtras( request.getDisponibilidadHorasExtras() );
        usuario.setRol( request.getRol() );
        usuario.setActivo( request.getActivo() );

        return usuario;
    }

    @Override
    public Usuario editRequestToUser(EditarUsuarioRequest request) {
        if ( request == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setContraseña( request.getPassword() );
        usuario.setId( request.getId() );
        usuario.setNombre( request.getNombre() );
        usuario.setApellido1( request.getApellido1() );
        usuario.setApellido2( request.getApellido2() );
        usuario.setEmail( request.getEmail() );
        usuario.setTelefono( request.getTelefono() );
        usuario.setCentroTrabajo( request.getCentroTrabajo() );
        usuario.setPuesto( request.getPuesto() );
        usuario.setLocalidad( request.getLocalidad() );
        usuario.setPreferenciasHorarias( request.getPreferenciasHorarias() );
        usuario.setDisponibilidadHorasExtras( request.getDisponibilidadHorasExtras() );
        usuario.setRol( request.getRol() );
        usuario.setActivo( request.getActivo() );

        return usuario;
    }

    @Override
    public UsuarioVehiculosResponse usuarioToUsuarioVehiculosResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioVehiculosResponse usuarioVehiculosResponse = new UsuarioVehiculosResponse();

        usuarioVehiculosResponse.setId( usuario.getId() );
        usuarioVehiculosResponse.setNombre( usuario.getNombre() );
        usuarioVehiculosResponse.setApellido1( usuario.getApellido1() );
        usuarioVehiculosResponse.setApellido2( usuario.getApellido2() );
        usuarioVehiculosResponse.setEmail( usuario.getEmail() );
        usuarioVehiculosResponse.setVehiculos( vehiculoListToVehiculoDTOList( usuario.getVehiculos() ) );
        usuarioVehiculosResponse.setActivo( usuario.getActivo() );

        return usuarioVehiculosResponse;
    }

    @Override
    public UsuarioResponse usuarioToUsuarioResponse(Usuario usuario) {
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

    protected List<VehiculoDTO> vehiculoListToVehiculoDTOList(List<Vehiculo> list) {
        if ( list == null ) {
            return null;
        }

        List<VehiculoDTO> list1 = new ArrayList<VehiculoDTO>( list.size() );
        for ( Vehiculo vehiculo : list ) {
            list1.add( vehiculoToVehiculoDTO( vehiculo ) );
        }

        return list1;
    }
}
