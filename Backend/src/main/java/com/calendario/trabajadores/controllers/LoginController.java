package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.userservice.UserService;
import com.calendario.trabajadores.model.Usuario;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.model.login.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
//ojo: al escoger el model, el de springframework.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Login", description = "Endpoints para el login de los usuarios")
public class LoginController {
    @Autowired
    private UserService userService;
    //Endpoints
    @Operation(summary = "Login de usuario", description = "Endpoint para el login de usuario")
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario logueado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    //RequestParam especifica que son parámetros de la request
    public ResponseEntity<?> login(@RequestBody LoginRequest loginModel) {
        Optional<Usuario> usuario = userService.login(loginModel.username, loginModel.password);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Login incorrecto"));
        }
        return ResponseEntity.ok(usuario.get());
    }
}
