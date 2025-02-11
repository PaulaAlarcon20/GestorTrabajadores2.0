package com.calendario.trabajadores.model.database;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "viajes")
@NoArgsConstructor//Constructor vacio
@AllArgsConstructor//Constructor con todos los atributos
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date fecha;
    public Date hora;

    public String origen;
    public String destino;

    public int plazas;
    @Enumerated(EnumType.STRING)
    public EstadoViaje estado;

    @ManyToOne(fetch = FetchType.LAZY) //Relacion de muchos a uno
    @JoinColumn(name = "usuario_id", nullable = false) //Un usuario!!! (no conductor) por viaje, no puede ser nulo
    public Usuario conductor;
    @ManyToOne(fetch = FetchType.LAZY) //Relacion de muchos a uno
    @JoinColumn(name = "vehiculo_id", nullable = false) //Un vehiculo por viaje, no puede ser nulo
    @JsonBackReference //Para evitar la recursividad
    public Vehiculo vehiculo;
    @OneToMany(mappedBy = "viaje", fetch = FetchType.LAZY, orphanRemoval = false)
    public List<UsuarioViaje> usuarioViajes;

}
