package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.mappings.IUserMapper;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.CrearEditarUsuarioResponse;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Inyeccion de dependencias

    private final IUsuarioRepository userRepository;
    private final IUserMapper userMapper;

    //Contructor de UserService
    @Autowired
    public UserService(IUsuarioRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Metodo para hacer login
    public Optional<UsuarioDTO> login(String username, String password) {
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        System.out.println("llamando a la base de datos");
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        if (passWordCorrecta) {
            UsuarioDTO tempDTO = new UsuarioDTO();
            //mapeamos los datos del usuario a un DTO
            tempDTO.id = usuario.get().id;
            tempDTO.nombre = usuario.get().nombre;
            tempDTO.apellido1 = usuario.get().apellido1;
            tempDTO.apellido2 = usuario.get().apellido2;
            tempDTO.email = usuario.get().email;
            return Optional.of(tempDTO);
        } else {
            return Optional.empty();
        }
    }

    //Metodo para crear un usuario
    public Optional<CrearEditarUsuarioResponse> crearUsuario(CrearUsuarioRequest request) {
        //Buscar por email si el usuario ya existe
        var usuarioEsxists = userRepository.findUsuarioByEmail(request.getEmail());
        if (usuarioEsxists.isPresent()) {
            //Si el usuario ya existe devolvemos un Optional vacio
            return Optional.empty();
        }
        //Si no existe, creamos un nuevo usuario
        var usuario = userMapper.createRequestToUser(request);
        //Activamos el usuario por defecto
        usuario.setActivo(true);
        //Guardamos el usuario en la base de datos
        var usuarioSaved = userRepository.save(usuario);
        var crearteResponse = userMapper.userToCreateEditResponse(usuarioSaved);
        return Optional.of(crearteResponse);
    }


    public List<Usuario> usuariosConVehiculosActivos() {
        return userRepository.findUsuariosWithActiveVehiculos();
    }

    public List<UsuarioDTO> usuariosConVehiculosActivosDTO() {
        return userRepository.findUsuariosWithTheirActiveVehiculos();
    }

    public List<VehiculoDTO> vehiculosByUsuario(String email) {
        return userRepository.findVehiculosByUsuario(email);
    }

    //Metodo para editar un usuario
    public Optional<CrearEditarUsuarioResponse> editUsuario(EditarUsuarioRequest request) {
        var usuario = userRepository.findById(request.getId());
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        var Usuario = userMapper.editRequestToUser(request);
        var usuarioSaved = userRepository.save(Usuario);
        var response = userMapper.userToCreateEditResponse(usuarioSaved);

        return Optional.of(response);
    }

    //Metodo para desactivar un usuario
    public Optional<CrearEditarUsuarioResponse> softDelete(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.setActivo(false);
        Usuario usuarioSaved = userRepository.save(temp);
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        return Optional.of(response);
    }

    //Metodo para reactivar a un usuario
    public Optional<UsuarioDTO> reactivar(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.activo = true;
        userRepository.save(temp);
        return Optional.of(new UsuarioDTO(temp.id, temp.nombre, temp.apellido1, temp.apellido2, temp.email, null, temp.activo));
    }

    //En lugar de tener varios metodos para encontrar según los parametros que tenga, creamos un método que
    //sea capaz de devolvernos una lista de usuarios según los parametros que le pasemos
    public Optional<List<CrearEditarUsuarioResponse>> listar(Optional<Boolean> activo) {
        if (activo.isEmpty()) {
            //si no se pasa el parametro activo, devolvemos todos los usuarios
            var lista = userRepository.findAll();

            var list = lista.stream().map(usuario -> userMapper.userToCreateEditResponse(usuario)).toList();
            return Optional.of(list);

        } else {
            //si se pasa el parametro activo y es true o false, devolvemos los usuarios activos o inactivos respectivamente
            var lista = userRepository.findByActivo(activo.get());
            //var lista = userRepository.findByActivo(true);
            //var lista = userRepository.findByActivo(false);
            var list = lista.stream().map(usuario -> userMapper.userToCreateEditResponse(usuario)).toList();
            return Optional.of(list);
        }
    }

    //Metodo para hacer logout (no desarrollado)******
    public Optional<UsuarioDTO> logout(String username, String password) {

        return Optional.empty();

    }

    //Metodo para borrar un usuario (IMPORTANTE: uso solo de admin!! no para usuarios!)
    public Optional<CrearEditarUsuarioResponse> borrar(Long id, String email) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        if (!usuario.get().getEmail().equals(email)) {
            return Optional.empty();
        }
        userRepository.delete(usuario.get());
        return Optional.of(userMapper.userToCreateEditResponse(usuario.get()));
    }

    public Optional<CrearEditarUsuarioResponse> getUsuario(String email) {
        var usuario = userRepository.findUsuarioByEmail(email);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.userToCreateEditResponse(usuario.get()));
    }

    public Optional<CrearEditarUsuarioResponse> getUsuario(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.userToCreateEditResponse(usuario.get()));
    }
}
