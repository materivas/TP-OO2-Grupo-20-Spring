package com.oo2.grupo20.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PersonaDTO {
	 	
		private Long id;

	    @NotBlank(message = "El nombre no puede estar vacío")
	    private String nombre;

	    @NotBlank(message = "El apellido no puede estar vacío")
	    private String apellido;

	    @NotNull(message = "El DNI es obligatorio")
	    private Integer dni;

	    @Email(message = "El email no es válido")
	    private String email;

	    private LocalDate fechaNacimiento;

	    private boolean estado = true;

	    @NotBlank(message = "El nombre de usuario es obligatorio")
	    private String username;

	    @NotBlank(message = "La contraseña es obligatoria")
	    private String password;
}
