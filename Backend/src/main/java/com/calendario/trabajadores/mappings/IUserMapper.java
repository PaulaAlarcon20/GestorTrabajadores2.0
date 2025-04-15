package com.calendario.trabajadores.mappings;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    //Mapeo de los atributos de la clase Usuario a los atributos de la clase UsuarioVehiculosResponse
    UsuarioResponse userToCreateEditResponse(Usuario userDB);

    //Mapeo de los atributos de la clase CrearUsuarioRequest a los atributos de la clase Usuario
    @Mapping(source = "password", target = "contraseña")
    Usuario createRequestToUser(CrearUsuarioRequest request);

    //Mapeo de los atributos de la clase EditarUsuarioRequest a los atributos de la clase Usuario
    @Mapping(source = "password", target = "contraseña")
    Usuario editRequestToUser(EditarUsuarioRequest request);


    UsuarioVehiculosResponse usuarioToUsuarioVehiculosResponse(Usuario usuario);

    UsuarioResponse usuarioToUsuarioResponse(Usuario usuario);
    // Mapea la lista de viajes a la propiedad 'viajes'
    @Mapping(source = "usuario.viajes", target = "viajes")
    UsuarioViajeResponse userToUsuarioViajeResponse(Usuario usuario);
}
