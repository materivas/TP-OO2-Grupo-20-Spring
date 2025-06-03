package exceptions;

@SuppressWarnings("serial")
public class PersonaNotFoundException extends RuntimeException{
	public PersonaNotFoundException(Long id) {
	     super("Persona no encontrada con ID: " + id);
	 }
}


