package com.oo2.grupo20.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
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
public class Especialidad {
	 
	 @Id
	 @GeneratedValue (strategy = GenerationType.IDENTITY)
	 private Long idEspecialidad;
	 
	 private String nombre;

	 // Relación ManyToMany con Empleado (bidireccional)
	    @ManyToMany(mappedBy = "especialidades", fetch = FetchType.LAZY)
	    private Set<Empleado> empleados = new HashSet<>();
	 

}
