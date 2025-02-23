package com.calendario.trabajadores.controllers;

import com.calendario.trabajadores.services.turno.TurnoService;
import com.calendario.trabajadores.services.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Turno", description = "Endpoints para viajes")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private UserService userService;

    /*Crear un turno
    @Operation(summary = "Creación de un turno", description = "Endpoint para crear un turno")
    @PostMapping("/turno/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turno creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarTurnoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    }
    )
    public ResponseEntity<?> create(@RequestBody CrearTurnoRequest request) {
        Optional<UsuarioResponse> usuario = userService.crearTurno(request);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "el turno ya existe"));
        }
        return ResponseEntity.ok(turno.get());
    }
     */

}
