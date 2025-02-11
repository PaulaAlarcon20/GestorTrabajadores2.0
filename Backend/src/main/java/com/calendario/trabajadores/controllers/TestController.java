package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.model.dto.UsuarioDTO;
import com.calendario.trabajadores.model.dto.VehiculoDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import com.calendario.trabajadores.model.database.Usuario;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Tag(name = "Test", description = "Endpoints para test")
public class TestController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Listar usuarios con vehiculos", description = "Endpoint para listar usuarios con vehiculos")
    @GetMapping("/listUsuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )

    public ResponseEntity<?> listaActivos () {
        return ResponseEntity.ok(userService.usuariosConVehiculosActivos());
    }

    @Operation(summary = "Listar usuarios con vehiculos", description = "Endpoint para listar usuariosDTO con vehiculos")
    @GetMapping("/listUsuariosDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listaActivosDTO () {
        return ResponseEntity.ok(userService.usuariosConVehiculosActivosDTO());
    }

    @Operation(summary = "Listar usuarios con vehiculos", description = "Endpoint para listar vehiculos de un usuarioDTO")
    @GetMapping("/listVehiculosByUserDTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista creada",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VehiculoDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> listVehiculosByUserDTO (@RequestParam String email) {
        return ResponseEntity.ok(userService.vehiculosByUsuario(email));
    }
}
