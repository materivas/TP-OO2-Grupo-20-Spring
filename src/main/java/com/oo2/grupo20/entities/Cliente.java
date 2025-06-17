package com.oo2.grupo20.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cliente extends Persona {
	
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Turno> turnos = new HashSet<>();

    public Cliente(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Cliente(String nombre) {
		this.nombre = nombre;
	}
    
}
