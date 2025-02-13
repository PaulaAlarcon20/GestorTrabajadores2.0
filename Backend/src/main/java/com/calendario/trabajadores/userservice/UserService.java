package com.calendario.trabajadores.userservice;

import com.calendario.trabajadores.model.Usuario;
import com.calendario.trabajadores.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUsuarioRepository userRepository;

    public Optional<Usuario> login(String username, String password){
        Optional<Usuario> usuario = userRepository.findUsuarioByEmail(username);
        System.out.println("llamando a la base de datos");
        if(usuario.isEmpty()){
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        return passWordCorrecta ? usuario : Optional.empty();
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
}
