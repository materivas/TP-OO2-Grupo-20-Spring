package com.oo2.grupo20.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmpleado;

    private String nombre;
    private String apellido;
    private String CUIL;
    private boolean estaDisponible;

    // Relación ManyToOne con Establecimiento
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_establecimiento") // FK en la tabla Empleado
    private Establecimiento establecimiento;

    // Relación ManyToMany con Especialidad
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "empleado_especialidad", // Tabla intermedia
        joinColumns = @JoinColumn(name = "id_empleado"),
        inverseJoinColumns = @JoinColumn(name = "id_especialidad")
    )
    
    private Set<Especialidad> especialidades = new HashSet<>();
    
    
    
    public Empleado(long idEmpleado, String name, String apellido) {
		this.idEmpleado = idEmpleado;
		this.nombre = name;
		this.apellido = apellido;
	}

	public Empleado(String name) {
		this.nombre = name;
	}
    
    
    
    
    
    
    
}







