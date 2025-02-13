package com.calendario.trabajadores.repository.usuario;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomUsuarioRepository {
    //Métodos de consulta

    //Método para buscar un AdministradorServicio por su correo electrónico
    Optional<Usuario> findUsuarioByEmail(String email);
    //Devolvemos una lista de usuarios que tengan vehiculos activos
    @Query("SELECT u FROM Usuario u JOIN u.vehiculos v WHERE v.activo = true")
    List<Usuario> findUsuariosWithActiveVehiculos();

    //Si quiero obtener usuarios con sus vehiculos activos nada más, utilizo DTOS
    List<UsuarioDTO> findUsuariosWithTheirActiveVehiculos();
    List<VehiculoDTO> findVehiculosByUsuario(String email);
}
