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
public class Establecimiento {
	 
	 @Id
	 @GeneratedValue (strategy = GenerationType.IDENTITY)
	 private long idEstablecimiento;
	 
	 private String localidad;
	 private String nombre;

	    // Relación OneToMany con Empleado (bidireccional)
	    @OneToMany(mappedBy = "establecimiento", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	    private Set<Empleado> empleados = new HashSet<>();
	 

}