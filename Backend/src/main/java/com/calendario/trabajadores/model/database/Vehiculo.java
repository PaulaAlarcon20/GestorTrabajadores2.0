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

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehiculos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"matricula"}),

})
public class Vehiculo extends CamposComunes {
    //Es un campo identificador de la tabla y además es identity, es decir, se autoincrementa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modeloCoche;
    @Column(name = "matricula", unique = true, nullable = false)
    private String matricula;
    private int plazas;
    @ManyToOne(fetch = FetchType.LAZY)
    //Aseguro la relacion de un vehiculo por usuario, no puede ser nulo y el usuario debe ser unico
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    //public Usuario usuario = new Usuario();
    private Usuario usuario;
    //vehiculo activo o no
    private Boolean activo;
}
