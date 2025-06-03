package com.oo2.grupo20.dto.request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Data
public class PersonaRequest {
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;
	    
	@NotBlank(message = "El apellido es obligatorio")
	private String apellido;
	    
	@NotNull(message = "El DNI es obligatorio")
	@Min(value = 1000000, message = "DNI inválido")
	private Integer dni;
	    
	@Email(message = "El email debe ser válido")
	@NotBlank(message = "El email es obligatorio") 
	private String email;
	    
	@Past(message = "La fecha de nacimiento debe ser en el pasado")
	private LocalDate fechaNacimiento;
	    
	private Boolean estado;
}

