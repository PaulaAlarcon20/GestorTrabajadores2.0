package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.Usuario;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.userservice.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Account", description = "Endpoints para modificación y creación de usuarios")
public class AccountController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Creación de usuario", description = "Endpoint para crear un usuario")
    @PostMapping("/creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> create (@RequestBody Usuario usuarioModel) {
        //Forzamos que el usuario registrado sea un usuario normal (capa extra de seguridad)
        usuarioModel.rol = "user";
        Optional<Usuario> usuario = userService.crearUsuario(usuarioModel);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario ya existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }



    @Operation(summary = "Creación de admin", description = "Endpoint para crear un admin")
    @PostMapping("/adminCreation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> createAdmin(@RequestBody Usuario usuarioModel) {
        //Forzamos que el usuario registrado sea un usuario normal (capa extra de seguridad)
        usuarioModel.rol = "admin";
        Optional<Usuario> usuario = userService.crearUsuario(usuarioModel);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario ya existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }
}
