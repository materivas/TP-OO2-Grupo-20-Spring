package com.oo2.grupo20.entities;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import jakarta.persistence.*;


@Entity
@Data
public class Servicio {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServicio;
    
	private String nombreServicio;
	private String descripcion;
	private int duracion;
	private double precio;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	//Relacion Many-To-One con establecimiento
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "establecimiento_id", nullable = false)
	private Establecimiento establecimiento;
	
	//Relacion One-To-Many con dia
	@OneToMany (mappedBy ="servicio", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Dia> dias = new HashSet<>();
	
	//Relacion One-To-Many con turno
	@OneToMany (mappedBy ="servicio", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE} ) 
	private Set<Turno> turnos = new HashSet<>();
	
    // Constructor sin argumentos en caso de ser necesario
    public Servicio() {
    }

    // Constructor con argumentos 
    public Servicio(Long idServicio,String nombreServicio,String descripcion,int duracion, double precio, LocalTime horaInicio, LocalTime horaFin, Establecimiento establecimiento) {
        this.idServicio=idServicio;
        this.nombreServicio=nombreServicio;
        this.descripcion=descripcion;
        this.duracion=duracion;
        this.precio=precio;
        this.horaInicio=horaInicio;
        this.horaFin=horaFin;
        this.establecimiento=establecimiento;
    }
}
