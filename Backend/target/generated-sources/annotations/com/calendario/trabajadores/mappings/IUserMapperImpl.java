package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-23T14:01:35+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
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
}
