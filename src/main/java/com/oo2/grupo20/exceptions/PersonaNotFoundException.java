package com.oo2.grupo20.exceptions;

@SuppressWarnings("serial")
public class PersonaNotFoundException extends RuntimeException{
	public PersonaNotFoundException(Long id) {
	     super("Persona no encontrada con ID: " + id);
	 }
}


