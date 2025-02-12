package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.dto.usuario.UsuarioDTO;
import com.calendario.trabajadores.model.errorresponse.ErrorResponse;
import com.calendario.trabajadores.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "User", description = "Endpoints para modificación y creación de usuarios")
public class UserController {
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

    @Operation(summary = "Editar datos de usuario", description = "Endpoint para editar datos de usuario")
    @PostMapping("/editUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user modificado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> editarUsuario(@RequestBody UsuarioDTO model) {
        Optional<UsuarioDTO> usuario = userService.editUsuario(model);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    @Operation(summary = "dar de baja usuario", description = "Endpoint para dar de baja usuario")
    @PostMapping("/deactivateUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user desactivado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> bajaUsuario(@RequestParam Long id) {
        Optional<UsuarioDTO> usuario = userService.softDelete(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    @Operation(summary = "reactivar usuario", description = "Endpoint para reactivar usuario")
    @PostMapping("/reactivateUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user reactivated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> reactivar(@RequestParam Long id) {
        Optional<UsuarioDTO> usuario = userService.reactivar(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    @Operation(summary = "listar usuarios", description = "Endpoint para listar todos los usuarios")
    @PostMapping("/listAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user reactivated",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )

    public ResponseEntity<?> listarAll(@RequestParam(value = "activo") Optional<Boolean> activo) {
        Optional<UsuarioDTO> usuario = userService.listar(activo);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

}
