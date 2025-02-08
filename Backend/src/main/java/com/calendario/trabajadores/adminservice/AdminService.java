package com.calendario.trabajadores.adminservice;

import com.calendario.trabajadores.model.AdministradorServicio;
import com.calendario.trabajadores.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private IAdminRepository adminRepository;

    public boolean login(String username, String password){
        Optional<AdministradorServicio> usuario = adminRepository.findAdministradorByEmail(username);
        System.out.println("llamando a la base de datos");
        if(!usuario.isPresent()){
            return false;
        }
        boolean passWordCorrecta = usuario.get().getContraseña().equals(password);
        return passWordCorrecta;
    }
}
