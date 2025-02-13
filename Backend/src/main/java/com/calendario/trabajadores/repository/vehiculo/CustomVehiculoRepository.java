package com.calendario.trabajadores.repository.vehiculo;

import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface CustomVehiculoRepository{

    //Listar vehiculos por usuario
    List<Vehiculo> listarVehiculosUsuario(Long usuarioId);


}
