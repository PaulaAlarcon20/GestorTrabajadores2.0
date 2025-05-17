package com.calendario.trabajadores.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.trabajadores.model.database.CambioTurno;
import com.calendario.trabajadores.model.dto.cambioTurno.CambioTurnoResponse;
import com.calendario.trabajadores.model.dto.cambioTurno.CrearCambioTurnoRequest;
import com.calendario.trabajadores.model.errorresponse.GenericResponse;
import com.calendario.trabajadores.services.turno.CambioTurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*") // Permite solicitudes desde el cliente Flutter
@Tag(name = "CambioTurno", description = "Endpoints para gestión de turnos")
public class CambioTurnoController {

    @Autowired
    private CambioTurnoService cambioTurnoService;

    @Operation(summary = "Cambios de turnos", description = "Endpoint para Cambios de turnos")
    @GetMapping("/api/cambioTurnos")
	public List<CambioTurno> obtenerCambioTurnos(){
        System.out.println("Entra a controller obtenerCambioTurnos");
		return cambioTurnoService.obtenerCambioTurnos();
	}

    @Operation(summary = "Solicitudes por Usuario", description = "Endpoint para Solicitudes por Usuario")
    @GetMapping("/api/solicitudes")
	public List<CambioTurno> obtenerSolicitudes(@RequestParam(required = true) int userId){
        System.out.println("Entra a controller obtenerSolicitudes");
		return cambioTurnoService.obtenerSolicitudes(userId);
	}

    @Operation(summary = "Peticiones por Usuario", description = "Endpoint para Peticiones por Usuario")
    @GetMapping("/api/peticiones")
	public List<CambioTurno> obtenerPeticiones(@RequestParam(required = true) int userId){
        System.out.println("Entra a controller obtenerPeticiones");
		return cambioTurnoService.obtenerPeticiones(userId);
	}

    @Operation(summary = "Nueva solicitud", description = "Endpoint para Nueva solicitud")
    @PostMapping("/api/new_sol")
	public GenericResponse<CambioTurnoResponse> nuevaSolicitud(@RequestBody CrearCambioTurnoRequest request)
    {
        System.out.println("Entra a controller nuevaSolicitud");
		return cambioTurnoService.guardarSolicitud(request);
	}

    // // Crear un turno
    // @Operation(summary = "Creación de turno", description = "Endpoint para crear un turno")
    // @PostMapping("/turno/crear")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Turno creado",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarTurnoResponse.class))),
    //         @ApiResponse(responseCode = "400", description = "Bad Request",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    // )
    // public ResponseEntity<?> crearTurno(@RequestBody CrearTurnoRequest input) {
    //     GenericResponse<CrearEditarTurnoResponse> respuestaServicio = turnoService.crearTurno(input);

    //     if (respuestaServicio.getError() != null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
    //     }
    //     return ResponseEntity.ok(respuestaServicio);
    // }

    // //Modificar un turno
    // @Operation(summary = "Modificar turno", description = "Endpoint para modificar un turno")
    // @PostMapping("/turno/editar")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Turno modificado",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarTurnoResponse.class))),
    //         @ApiResponse(responseCode = "400", description = "Bad Request",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    // )
    // public ResponseEntity<GenericResponse<CrearEditarTurnoResponse>> editarTurno(@RequestBody EditarTurnoRequest input) {
    //     GenericResponse<CrearEditarTurnoResponse> respuestaServicio = turnoService.modificarTurno(input);

    //     if (respuestaServicio.getError() != null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
    //     }

    //     return ResponseEntity.ok(respuestaServicio);
    // }

    // // Activar o desactivar un turno
    // @Operation(summary = "Activar/desactivar turno", description = "Endpoint para activar/desactivar un turno")
    // @PostMapping("/turno/toggle")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Turno activado/desactivado",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = CrearEditarTurnoResponse.class))),
    //         @ApiResponse(responseCode = "400", description = "Bad Request",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    // )
    // public ResponseEntity<?> toggleTurno(@RequestBody Long id) {
    //     GenericResponse<CrearEditarTurnoResponse> respuestaServicio = turnoService.toggleTurno(id);
    //     if (respuestaServicio.getError() != null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
    //     }
    //     return ResponseEntity.ok(respuestaServicio);
    // }

    // // Listar turnos de un usuario
    // @Operation(summary = "Listar turnos de un usuario", description = "Endpoint para listar todos los turnos de un usuario")
    // @GetMapping("/turno/list")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Turnos listados",
    //                 content = @Content(mediaType = "application/json",
    //                         array = @ArraySchema(schema = @Schema(implementation = CrearEditarTurnoResponse.class)))),

    //         @ApiResponse(responseCode = "400", description = "Bad Request  - Parámetro inválido o falta de turnos",
    //                 content = @Content(mediaType = "application/json",
    //                         schema = @Schema(implementation = ErrorResponse.class)))
    // })
    // public ResponseEntity<GenericResponse<List<CrearEditarTurnoResponse>>> listarTurnos(
    //         @RequestParam(value = "usuarioId") Long usuarioId,
    //         @RequestParam(value = "estado", required = false) Optional<String> estado) {

    //     // Convertir el estado (String) a un Enum de EstadoTurno
    //     Optional<EstadoTurno> estadoTurno = estado.map(estadoString -> {
    //         try {
    //             return EstadoTurno.valueOf(estadoString);
    //         } catch (IllegalArgumentException e) {
    //             // Si no se encuentra el valor, devolvemos null
    //             return null;
    //         }
    //     });

    //     // Llamar al servicio para obtener los turnos, pasando el estado convertido
    //     GenericResponse<List<CrearEditarTurnoResponse>> respuestaServicio = turnoService.listarTurnos(usuarioId, estadoTurno);

    //     // Comprobar si hay errores en la respuesta del servicio
    //     if (respuestaServicio.getError() != null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
    //     }

    //     // Si no hay errores, devolver la respuesta exitosa
    //     return ResponseEntity.ok(respuestaServicio);
    // }


    // // Eliminar un turno
    // @Operation(summary = "Eliminar turno", description = "Endpoint para eliminar un turno")
    // @DeleteMapping("/turno/borrar")
    // @ApiResponses(value = {
    //         @ApiResponse(responseCode = "200", description = "Turno eliminado",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
    //         @ApiResponse(responseCode = "400", description = "Bad Request - Usuario no autorizado",
    //                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))}
    // )
    // public ResponseEntity<?> borrarTurno(@RequestParam Long idTurno, @RequestParam Long idUsuario) {
    //     GenericResponse<String> respuestaServicio = turnoService.borrarTurno(idTurno, idUsuario);

    //     if (respuestaServicio.getError() != null) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
    //     }
    //     return ResponseEntity.ok(respuestaServicio);
    // }
}