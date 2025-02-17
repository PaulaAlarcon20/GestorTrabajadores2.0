package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.dto.usuario.CrearUsuarioRequest;
import com.calendario.trabajadores.model.dto.usuario.CrearEditarUsuarioResponse;
import com.calendario.trabajadores.model.dto.usuario.EditarUsuarioRequest;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "User", description = "Endpoints para modificación y creación de usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    //Endpoints
    //Crear usuario (O.K)
    @Operation(summary = "Creación de usuario", description = "Endpoint para crear un usuario")
    @PostMapping("/user/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> create(@RequestBody CrearUsuarioRequest request) {
        //Forzamos que el usuario registrado sea un usuario normal (capa extra de seguridad)
        request.rol = "user";
        Optional<CrearEditarUsuarioResponse> usuario = userService.crearUsuario(request);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario ya existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    //Crear usuario admin (O.K)
    @Operation(summary = "Creación de admin", description = "Endpoint para crear un admin")
    @PostMapping("/user/adminCreate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> createAdmin(@RequestBody CrearUsuarioRequest request) {
        //Forzamos que el usuario registrado sea un usuario normal (capa extra de seguridad)
        request.rol = "admin";
        Optional<CrearEditarUsuarioResponse> usuario = userService.crearUsuario(request);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario ya existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    //Listar información de un usuario (obtener el usuario con el id)
    @Operation(summary = "Listar información de un usuario", description = "Endpoint para listar información de un usuario")
    @GetMapping("/user/get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> getUsuario(@RequestParam Long id) {
        Optional<CrearEditarUsuarioResponse> usuario = userService.getUsuario(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }
    //Listar información de un usuario (obtener el usuario con el email)
    @Operation(summary = "Listar información de un usuario", description = "Endpoint para listar información de un usuario")
    @GetMapping("/user/get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> getUsuario(@RequestParam String email) {
        Optional<CrearEditarUsuarioResponse> usuario = userService.getUsuario(email);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    //Editar datos de usuario
    @Operation(summary = "Editar datos de usuario", description = "Endpoint para editar datos de usuario")
    @PostMapping("/user/edit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user modificado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> editarUsuario(@RequestBody EditarUsuarioRequest request) {
        var usuario = userService.editUsuario(request);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    //Dar de baja usuario (desactivar)
    @Operation(summary = "dar de baja usuario", description = "Endpoint para dar de baja usuario")
    @PostMapping("/user/deactivate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user desactivado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> bajaUsuario(@RequestParam Long id) {
        Optional<CrearEditarUsuarioResponse> usuario = userService.softDelete(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

    //Reactivar usuario
    @Operation(summary = "reactivar usuario", description = "Endpoint para reactivar usuario")
    @PostMapping("/user/reactivate")
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

    //Listar usuarios
    @Operation(summary = "listar usuarios", description = "Endpoint para listar todos los usuarios")
    @GetMapping("/user/list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user reactivated",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CrearEditarUsuarioResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listAll(@RequestParam(value = "activo") Optional<Boolean> activo) {
        Optional<List<CrearEditarUsuarioResponse>> usuario = userService.listar(activo);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "..."));
        }
        return ResponseEntity.ok(usuario.get());
    }
    //Borrado total usuario
    @Operation(summary = "borrar usuario", description = "Endpoint para borrar usuario")
    @DeleteMapping("/user/delete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "borrar usuario",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarUsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> borrarUsuario(@RequestParam Long id, String email) {
        Optional<CrearEditarUsuarioResponse> usuario = userService.borrar(id, email);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el usuario no existe"));
        }
        return ResponseEntity.ok(usuario.get());
    }

}
