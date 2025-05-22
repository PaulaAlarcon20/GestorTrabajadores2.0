package com.calendario.trabajadores.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.calendario.trabajadores.model.database.CambioTurno;
import com.calendario.trabajadores.model.database.PeticionTurno;
import com.calendario.trabajadores.model.dto.cambioTurno.CambioTurnoResponse;
import com.calendario.trabajadores.model.dto.cambioTurno.CrearCambioTurnoRequest;
import com.calendario.trabajadores.model.errorresponse.GenericResponse;
import com.calendario.trabajadores.services.turno.CambioTurnoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Component
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
	public ResponseEntity<?> nuevaSolicitud(@RequestParam int usuarioId, int jornadaId, LocalDate fechaSolicitada) // @RequestBody CrearCambioTurnoRequest request
    {
        System.out.println("Entra a controller nuevaSolicitud");
        System.out.println("Request recibido: " + usuarioId + " - "+ jornadaId + " - "+fechaSolicitada);

        CrearCambioTurnoRequest lNuevoCambio = new CrearCambioTurnoRequest(usuarioId,jornadaId,fechaSolicitada);

        GenericResponse<CambioTurnoResponse> respuestaServicio = cambioTurnoService.guardarSolicitud(lNuevoCambio);

        if (respuestaServicio.getError() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
        
        return ResponseEntity.ok(respuestaServicio);
	}

    @Operation(summary = "Aceptar Cambio Turno (Petición)", description = "Endpoint para Aceptar Cambio Turno (Petición)")
    @PostMapping("/api/aceptar_sol")
	public ResponseEntity<?> aceptarPeticion(@RequestParam int idCambioTurno, int usuarioAceptanteId) //  CrearCambioTurnoRequest request
    {
        System.out.println("Entra a controller aceptarSolicitud");
        System.out.println("Request recibido: " + idCambioTurno + " - "+ usuarioAceptanteId );

        GenericResponse<CambioTurnoResponse> respuestaServicio = cambioTurnoService.aceptarCambioTurno(idCambioTurno, usuarioAceptanteId);

        if (respuestaServicio.getError() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
        
        return ResponseEntity.ok(respuestaServicio);
	}

    @Operation(summary = "Eliminar Cambio Turno (Solicitud)", description = "Endpoint para Eliminar Cambio Turno (Solicitud)")
    @PostMapping("/api/delete_sol")
	public ResponseEntity<?> eliminarSolicitud(@RequestParam int idCambioTurno)
    {
        System.out.println("Entra a controller eliminarSolicitud");
        System.out.println("Request recibido: " + idCambioTurno  );

        GenericResponse<CambioTurnoResponse> respuestaServicio = cambioTurnoService.eliminarCambioTurno(idCambioTurno);

        if (respuestaServicio.getError() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
        
        return ResponseEntity.ok(respuestaServicio);
	}

    @Operation(summary = "Eliminar Cambio Turno (Solicitud)", description = "Endpoint para Eliminar Cambio Turno (Solicitud)")
    @PostMapping("/api/rechazar_sol")
	public ResponseEntity<?> rechazarSolicitud(@RequestParam int idCambioTurno)
    {
        System.out.println("Entra a controller rechazarSolicitud");
        System.out.println("Request recibido: " + idCambioTurno  );

        GenericResponse<CambioTurnoResponse> respuestaServicio = cambioTurnoService.modificarCambioTurno(idCambioTurno, PeticionTurno.RECHAZADA);

        if (respuestaServicio.getError() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuestaServicio);
        
        return ResponseEntity.ok(respuestaServicio);
	}

}