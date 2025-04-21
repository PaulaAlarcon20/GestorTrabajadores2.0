package com.calendario.trabajadores.repository.usuarioviaje;

import com.calendario.trabajadores.model.database.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUsuarioViajeRepository extends JpaRepository<UsuarioViaje, Long> {
    // Verificar si el usuario ya ha solicitado unirse al viaje en un estado específico
    boolean existsByUsuarioAndViajeAndEstado(Usuario usuario, Viaje viaje, EstadoUsuarioViaje estado);

    // Buscar la solicitud de un usuario para un viaje en estado pendiente
    Optional<UsuarioViaje> findByUsuarioIdAndViajeIdAndEstado(Long usuarioId, Long viajeId, EstadoUsuarioViaje estado);

    // Buscar todas las solicitudes aceptadas para un viaje
    List<UsuarioViaje> findByViajeIdAndEstado(Long viajeId, EstadoUsuarioViaje estado);

    // Buscar solicitudes por estado (si se necesita)
    List<UsuarioViaje> findByEstado(EstadoUsuarioViaje estado);

    // Buscar una solicitud específica por usuario y viaje
    Optional<UsuarioViaje> findByUsuarioAndViaje(Usuario usuario, Viaje viaje);

    // Verificar si el usuario ya está asociado a este viaje
    boolean existsByUsuarioAndViaje(Usuario usuario, Viaje viaje);
    public List<Viaje> findAllViajesByEstado(EstadoViaje estado);


}
