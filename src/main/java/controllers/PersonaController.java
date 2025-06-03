package controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.request.FiltroPersonasRequest;
import dto.request.PersonaRequest;
import dto.response.PersonaResponse;
import dto.response.PersonaResumenResponse;
import services.PersonaService;

@RestController
@RequestMapping("api/personas")
public class PersonaController {
	private final PersonaService personaService;

	public PersonaController(PersonaService personaService) {
		this.personaService = personaService;
	}

	@PostMapping
	public ResponseEntity<PersonaResponse> crearPersona(@RequestBody PersonaRequest request) {
		PersonaResponse response = personaService.createPersona(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonaResponse> obtenerPersona(@PathVariable Long id) {
		return ResponseEntity.ok(personaService.getPersonaById(id));
	}

	@GetMapping
	public Page<PersonaResumenResponse> listarPersonas(
		FiltroPersonasRequest filtro,
		@PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
		return personaService.searchPersonas(filtro, pageable);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
		personaService.deletePersona(id);
		return ResponseEntity.noContent().build();
	}
}
