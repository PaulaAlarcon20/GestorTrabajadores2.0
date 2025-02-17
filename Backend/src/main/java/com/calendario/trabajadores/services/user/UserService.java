package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioResponse;
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

    //Contructor de UserService
    @Autowired
    public UserService(IUsuarioRepository userRepository) {

        this.userRepository = userRepository;
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
    public Optional<CrearUsuarioResponse> crearUsuario(CrearUsuarioRequest request) {
        //Buscar por email si el usuario ya existe
        var usuarioEsxists = userRepository.findUsuarioByEmail(request.getEmail());
        if (usuarioEsxists.isPresent()) {
            //Si el usuario ya existe devolvemos un Optional vacio
            return Optional.empty();
        }
        //Si no existe, creamos un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido1(request.getApellido1());
        usuario.setApellido2(request.getApellido2());
        usuario.setEmail(request.getEmail());
        //TODO: Encriptar la contraseña antes de guardarla
        usuario.setContraseña(request.getPassword());
        //El usuario por defecto estará: activo, por eso no está en CrearUSuarioRequest
        usuario.setActivo(true);
        //Guardamos el usuario en la base de datos
        Usuario usuarioSaved = userRepository.save(usuario);
        //Creamos el Response DTO con los datos del usuario que guardamos
        CrearUsuarioResponse response = new CrearUsuarioResponse(
                usuarioSaved.getId(),
                usuarioSaved.getNombre(),
                usuarioSaved.getApellido1(),
                usuarioSaved.getApellido2(),
                usuarioSaved.getEmail(),
                usuarioSaved.getTelefono()
                usuarioSaved.getCentroTrabajo(),
                usuarioSaved.getPuesto(),
                usuarioSaved.getLocalidad(),
                usuarioSaved.getPreferenciasHorarias(),
                usuarioSaved.getDisponibilidadHorasExtras(),
                usuarioSaved.getRol(),
                usuarioSaved.isActivo()
        );
        return Optional.of(response);
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
    public Optional<UsuarioDTO> editUsuario(UsuarioDTO model) {
        var usuario = userRepository.findById(model.id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.nombre = model.nombre;
        temp.apellido1 = model.apellido1;
        temp.apellido2 = model.apellido2;
        temp.email = model.email;
        userRepository.save(temp);
        return Optional.of(model);
    }

    //Metodo para borrar un usuario
    public Optional<UsuarioDTO> softDelete(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.activo = false;
        userRepository.save(temp);
        return Optional.of(new UsuarioDTO(temp.id, temp.nombre, temp.apellido1, temp.apellido2, temp.email, null, temp.activo));
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
    public Optional<List<UsuarioDTO>> listar(Optional<Boolean> activo) {
        if (activo.isEmpty()) { //si no se pasa el parametro activo, devolvemos todos los usuarios
            var lista = userRepository.findAll();
            return Optional.of(lista.stream().map(usuario -> new UsuarioDTO(
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido1,
                    usuario.apellido2,
                    usuario.email,
                    null,
                    usuario.activo
            )).toList());
        } else if (activo.get()) { //si se pasa el parametro activo y es true, devolvemos los usuarios activos
            var lista = userRepository.findByActivo(true);
            return Optional.of(lista.stream().map(usuario -> new UsuarioDTO(
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido1,
                    usuario.apellido2,
                    usuario.email,
                    null,
                    usuario.activo
            )).toList());
        } else {
            var lista = userRepository.findByActivo(false); //si se pasa el parametro activo y es false, devolvemos los usuarios inactivos
            return Optional.of(lista.stream().map(usuario -> new UsuarioDTO(
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido1,
                    usuario.apellido2,
                    usuario.email,
                    null,
                    usuario.activo
            )).toList());
        }
    }

    //Metodo para hacer logout (no desarrollado)******
    public Optional<UsuarioDTO> logout(String username, String password) {

        return Optional.empty();

    }

    //Metodo para borrar un usuario
    public Optional<UsuarioDTO> borrar(Long id) {
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        userRepository.delete(usuario.get());
        return Optional.of(new UsuarioDTO(
                usuario.get().id,
                usuario.get().nombre,
                usuario.get().apellido1,
                usuario.get().apellido2,
                usuario.get().email,
                null,
                usuario.get().activo));
    }
}
