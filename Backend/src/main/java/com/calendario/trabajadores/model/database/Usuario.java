package com.calendario.trabajadores.model.database;

import com.calendario.trabajadores.model.common.CamposComunes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.transaction.TransactionUsageException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Entity es una anotación que indica que la clase es una entidad de la base de datos
@Entity
//Utilizo lombok para no tener que escribir los getters y setters
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario extends CamposComunes {
    //Es un campo identificador de la tabla y además es identity, es decir, se autoincrementa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Campo obligatorio
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido1;
    private String apellido2;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    //private String passwordHash; TODO proteccion contraseñas
    private String contraseña;
    private String telefono;
    private String centroTrabajo;
    private Puesto puesto;
    private String localidad;
    private String preferenciasHorarias;
    @Column(nullable = false)
    private String rol;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Vehiculo> vehiculos = new ArrayList<>();
    @OneToMany(mappedBy = "conductor", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Viaje> viajes = new ArrayList<>();
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<UsuarioViaje> usuarioViajes = new ArrayList<>();
    private Boolean activo = true;
    private Boolean disponibilidadHorasExtras =true;
    //Se sustituye al heredar de Campos Comunes
    /*
    @CreationTimestamp
    @Column(updatable = false)
    private Date fechaCreacion;
    @UpdateTimestamp
    private Date fechaModificacion;
    private String creadoPor;
    private String modificadoPor;*/
}
