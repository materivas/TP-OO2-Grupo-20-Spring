package com.oo2.grupo20.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
		
		@Email
		@NotBlank
		String username,
		
		@NotBlank
		String password) { 
	
	
}
