package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.adminservice.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//ojo: al escoger el model, el de springframework.
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    //Endpoints
    @GetMapping("/login")
    public String showLoginPage(){
        //llamada a la vista
        return "login";
    }

    @PostMapping("/login")
    //RequestParam especifica que son parámetros de la request
    public String login(@RequestParam String username,@RequestParam String password, Model model){
        var serviceResponse = adminService.login(username,password);
        if(!serviceResponse){
            model.addAttribute("error","invalid username");
            //llamada a la vista
            return "login";
        }
        //llamada a la vista
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(){
        //llamada a la vista
        return "welcome";
    }



}
