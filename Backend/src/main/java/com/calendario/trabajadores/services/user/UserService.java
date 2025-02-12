package com.calendario.trabajadores.services.user;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import com.calendario.trabajadores.repository.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUsuarioRepository userRepository;

    @Autowired
    public UserService(IUsuarioRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<UsuarioDTO> login(String username, String password){
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        System.out.println("llamando a la base de datos");
        if(usuario.isEmpty()){
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        if (passWordCorrecta){
            UsuarioDTO tempDTO = new UsuarioDTO();
            //mapeamos los datos del usuario a un DTO
            tempDTO.id = usuario.get().id;
            tempDTO.nombre = usuario.get().nombre;
            tempDTO.apellido1 = usuario.get().apellido1;
            tempDTO.apellido2 = usuario.get().apellido2;
            tempDTO.email = usuario.get().email;
            return Optional.of(tempDTO);
        }else{
            return Optional.empty();
        }
    }
    public Optional<Usuario> crearUsuario(Usuario usuario){

        var userExists = userRepository.findUsuarioByEmail(usuario.email);
        if (userExists.isEmpty()){
            //si el usuario no existe, lo creamos
            var resultado = userRepository.save(usuario);
            return Optional.of(resultado);
        }//si el usuario ya existe devolvemos un error
        else {
         return Optional.empty();
        }
    }
    public List<Usuario> usuariosConVehiculosActivos(){
        return userRepository.findUsuariosWithActiveVehiculos();
    }

    public List<UsuarioDTO> usuariosConVehiculosActivosDTO(){
        return userRepository.findUsuariosWithTheirActiveVehiculos();
    }

    public List<VehiculoDTO> vehiculosByUsuario(String email){
        return userRepository.findVehiculosByUsuario(email);
    }

    //Metodo para editar un usuario
    public Optional<UsuarioDTO> editUsuario(UsuarioDTO model){
        var usuario = userRepository.findById(model.id);
        if (usuario.isEmpty()){
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
    public Optional<UsuarioDTO> softDelete(Long id){
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()){
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.activo = false;
        userRepository.save(temp);
        return Optional.of(new UsuarioDTO(temp.id,temp.nombre,temp.apellido1,temp.apellido2,temp.email,null,temp.activo));
    }

    //Metodo para reactivar a un usuario
    public Optional<UsuarioDTO> reactivar(Long id){
        var usuario = userRepository.findById(id);
        if (usuario.isEmpty()){
            return Optional.empty();
        }
        Usuario temp = usuario.get();
        temp.activo = true;
        userRepository.save(temp);
        return Optional.of(new UsuarioDTO(temp.id,temp.nombre,temp.apellido1,temp.apellido2,temp.email,null,temp.activo));
    }

    //En lugar de tener varios metodos para encontrar según los parametros que tenga, creamos un método que
    //sea capaz de devolvernos una lista de usuarios según los parametros que le pasemos
    public Optional<List<UsuarioDTO>> listar(Optional<Boolean> activo){
        if (activo.isEmpty()){ //si no se pasa el parametro activo, devolvemos todos los usuarios
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
        }
        if (activo.get()){ //si se pasa el parametro activo, devolvemos solo los usuarios activos o no activos
            return Optional.of(userRepository.findByActivo("activo",true).stream().map(usuario -> new UsuarioDTO(
                    usuario.id,
                    usuario.nombre,
                    usuario.apellido1,
                    usuario.apellido2,
                    usuario.email,
                    null,
                    usuario.activo
            )).toList());
        }else{
            return Optional.empty();
        }
    }
}
