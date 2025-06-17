package com.oo2.grupo20.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
