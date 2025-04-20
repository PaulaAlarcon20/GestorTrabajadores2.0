package com.calendario.trabajadores.model.database;

import com.calendario.trabajadores.model.common.CamposComunes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "viajes")
@NoArgsConstructor//Constructor vacio
@AllArgsConstructor//Constructor con todos los atributos
public class Viaje extends CamposComunes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private String origen;
    private String destino;
    private int plazas;
    @Enumerated(EnumType.STRING)
    private EstadoViaje estado;

    // Un conductor por viaje, obligatorio
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario conductor;

    // Un vehículo por viaje, obligatorio
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false)
    @JsonBackReference
    private Vehiculo vehiculo;

    /*F/
    /*@ManyToOne(fetch = FetchType.LAZY) //Relacion de muchos a uno
    @JoinColumn(name = "usuario_id", nullable = false) //Un usuario!!! (no conductor) por viaje, no puede ser nulo
    private Usuario conductor = new Usuario();
    @ManyToOne(fetch = FetchType.LAZY) //Relacion de muchos a uno
    @JoinColumn(name = "vehiculo_id", nullable = false) //Un vehiculo por viaje, no puede ser nulo
    @JsonBackReference //Para evitar la recursividad
    private Vehiculo vehiculo = new Vehiculo();*/

    //Usuarios viajeros, ocupantes de plazas
    @OneToMany(mappedBy = "viaje", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<UsuarioViaje> usuarioViajes = new ArrayList<>();

}
