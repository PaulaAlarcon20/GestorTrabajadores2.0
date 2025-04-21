package com.calendario.trabajadores.model.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario_viaje")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioViaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    //El campo usuario no puede ser null
    //Obligatorio establecer un usuario al crear un UsuarioViaje
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario = new Usuario();
    @ManyToOne
    @JoinColumn(name = "viaje_id",nullable = false)
    private Viaje viaje = new Viaje();
    // Estado de la solicitud del usuario para unirse al viaje
    @Enumerated(EnumType.STRING)
    private EstadoUsuarioViaje estado;
}

