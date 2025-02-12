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
}
