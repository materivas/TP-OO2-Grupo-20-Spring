package com.oo2.grupo20.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Dia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDia;

    private LocalDate fecha;

    //Relacion One-To-Many con turnos
    @OneToMany(mappedBy = "dia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Turno> turnos = new HashSet<>();

    //Relacion Many-To-One con servicio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;


    // Constructor con argumentos 
    public Dia(long idDia, LocalDate fecha, Servicio servicio) {
        this.idDia = idDia;
        this.fecha = fecha;
        this.servicio = servicio;
    }
}

