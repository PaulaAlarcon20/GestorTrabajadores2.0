package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.adminservice.AdminService;
import com.calendario.trabajadores.model.login.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
//ojo: al escoger el model, el de springframework.
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;
    //Endpoints
    @PostMapping("/login")
    //RequestParam especifica que son parámetros de la request
    public String login(@RequestBody LoginModel loginModel){
        var serviceResponse = adminService.login(loginModel.getUsername(),loginModel.getPassword());
        if(!serviceResponse){
            //llamada a la vista
            return "error";
        }
        //llamada a la vista
        return "ok";
    }

}
