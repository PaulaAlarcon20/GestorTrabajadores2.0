package com.calendario.trabajadores.model.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento_calendario")
public class EventoCalendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descripcion;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private boolean diaCompleto;

    public EventoCalendario() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getInicio() { return inicio; }
    public void setInicio(LocalDateTime inicio) { this.inicio = inicio; }

    public LocalDateTime getFin() { return fin; }
    public void setFin(LocalDateTime fin) { this.fin = fin; }

    public boolean isDiaCompleto() { return diaCompleto; }
    public void setDiaCompleto(boolean diaCompleto) { this.diaCompleto = diaCompleto; }
}
