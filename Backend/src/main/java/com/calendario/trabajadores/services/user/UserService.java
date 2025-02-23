package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.mappings.IUserMapper;
import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.UsuarioResponse;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.UsuarioVehiculosResponse;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //Inyeccion de dependencias

    private final IUsuarioRepository userRepository;
    private final IUserMapper userMapper;

    //Contructor de UserService

    public UserService(IUsuarioRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //Metodo para hacer login
    public Optional<UsuarioVehiculosResponse> login(String username, String password) {
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        System.out.println("llamando a la base de datos");
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        if (passWordCorrecta) {
            UsuarioVehiculosResponse tempDTO = new UsuarioVehiculosResponse();
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

    //Metodo para hacer logout TODO:logout
    public Optional<UsuarioVehiculosResponse> logout(String username, String password) {
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        if (passWordCorrecta) {
            UsuarioVehiculosResponse tempDTO = new UsuarioVehiculosResponse();
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
    public Optional<UsuarioResponse> crearUsuario(CrearUsuarioRequest request) {
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


    //Metodo para editar un usuario
    public Optional<UsuarioResponse> editUsuario(EditarUsuarioRequest request) {
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
    public Optional<UsuarioResponse> softDelete(Long id) {
        //Buscamos al usuario por su id
        var usuario = userRepository.findById(id);
        //Si no existe devolvemos un Optional vacio
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        //Obtenemos el usuario
        Usuario temp = usuario.get();
        // Desactivamos al usuario
        temp.setActivo(false);
        //Guardamos el usuario en la base de datos
        Usuario usuarioSaved = userRepository.save(temp);
        //Mapeamos el usuario a un DTO
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        //Devolvemos el DTO
        return Optional.of(response);
    }

    //Metodo para reactivar a un usuario
    public Optional<UsuarioResponse> reactivar(Long id) {
        //Buscamos al usuario por su id
        var usuario = userRepository.findById(id);
        //Si no existe devolvemos un Optional vacio
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        //Obtenemos el usuario
        Usuario temp = usuario.get();
        //Activamos al usuario
        temp.setActivo(true);
        //Guardamos el usuario en la base de datos
        Usuario usuarioSaved = userRepository.save(temp);
        //Mapeamos el usuario a un DTO
        var response = userMapper.userToCreateEditResponse(usuarioSaved);
        //Devolvemos el DTO
        return Optional.of(response);
    }

    //Metodo para obtener un usuario por su email
    public Optional<UsuarioResponse> getUsuario(String email) {
        var usuario = userRepository.findUsuarioByEmail(email);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.userToCreateEditResponse(usuario.get()));
    }

    //Metodo para obtener un usuario por su id
    public Optional<UsuarioResponse> getUsuario(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userMapper.userToCreateEditResponse(usuario.get()));
    }

    //En lugar de tener varios metodos para encontrar según los parametros que tenga, creamos un método que
    //sea capaz de devolvernos una lista de usuarios según los parametros que le pasemos (O.K)
    public Optional<List<UsuarioResponse>> listar(Optional<Boolean> activo) {
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


    //Metodo para borrar un usuario (IMPORTANTE: no utilizar con usuarios! Riesgo de borrado de la base de datos)
    public Optional<UsuarioResponse> borrar(Long id, String email) {
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

    //Metodo para listar usuarios con vehiculos
    public List<UsuarioVehiculosResponse> listarUsuariosVehiculos(Optional<Boolean> activo) {
        //primero compruebo si es null
        if (activo.isEmpty()) {
            //Evitar una dependencia circular (findAll)
            var lista = userRepository.findAllUsuariosVehiculos();
            return lista;
        } else {
            //sabemos que no esta vacío
            var lista = userRepository.findAllUsuariosVehiculosFiltrados(activo.get());
            return lista;

        }

    }
}
