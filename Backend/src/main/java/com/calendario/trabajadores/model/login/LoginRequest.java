package com.calendario.trabajadores.model.login;

import lombok.Getter;
import lombok.Setter;

// Uso lombok para no tener que escribir los getters y setters
@Getter
@Setter
public class LoginRequest {
    public String username;
    public String password;

}
