package com.calendario.trabajadores.adminservice;

import com.calendario.trabajadores.model.Usuario;
import com.calendario.trabajadores.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private IAdminRepository adminRepository;

    public Optional<Usuario> login(String username, String password){
        Optional<Usuario> usuario = adminRepository.findUsuarioByEmail(username);
        System.out.println("llamando a la base de datos");
        if(usuario.isEmpty()){
            return Optional.empty();
        }
        boolean passWordCorrecta = usuario.get().contraseña.equals(password);
        return passWordCorrecta ? usuario : Optional.empty();
    }
}
