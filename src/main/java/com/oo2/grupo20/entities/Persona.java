package com.oo2.grupo20.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
@Getter @Setter @NoArgsConstructor
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String nombre;
    protected String apellido;

    @Column(unique = true, nullable = false)
    protected Integer dni;

    @Column(unique = true)
    protected String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
    
    protected LocalDate fechaDeNacimiento;
    protected boolean estado = true;  // Por defecto activo
}