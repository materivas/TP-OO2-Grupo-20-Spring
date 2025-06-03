package com.oo2.grupo20.services;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oo2.grupo20.dto.request.FiltroPersonasRequest;
import com.oo2.grupo20.dto.request.PersonaRequest;
import com.oo2.grupo20.dto.response.PersonaResponse;
import com.oo2.grupo20.dto.response.PersonaResumenResponse;
import com.oo2.grupo20.entities.Persona;
import com.oo2.grupo20.exceptions.DuplicatePersonaException;
import com.oo2.grupo20.exceptions.PersonaNotFoundException;
import com.oo2.grupo20.repositories.PersonaRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;

    // Configuración personalizada de ModelMapper (opcional)
    @PostConstruct
    public void configureModelMapper() {
        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)
            .setAmbiguityIgnored(true);
    }

    // ========== CRUD Básico ==========
    @Transactional(readOnly = true)
    public PersonaResponse getPersonaById(Long id) {
        return personaRepository.findById(id)
            .map(persona -> modelMapper.map(persona, PersonaResponse.class))
            .orElseThrow(() -> new PersonaNotFoundException(id));
    }

    public PersonaResponse createPersona(PersonaRequest request) {
        // Validación de unicidad
        if (personaRepository.existsByDni(request.getDni())) {
            throw new DuplicatePersonaException("DNI ya registrado: " + request.getDni());
        }

        if (request.getEmail() != null && personaRepository.existsByEmail(request.getEmail())) {
            throw new DuplicatePersonaException("Email ya registrado: " + request.getEmail());
        }

        Persona persona = modelMapper.map(request, Persona.class);
        persona = personaRepository.save(persona);
        return modelMapper.map(persona, PersonaResponse.class);
    }

    @Transactional
    public void deletePersona(Long id) {
        if (!personaRepository.existsById(id)) {
            throw new PersonaNotFoundException(id);
        }
        personaRepository.deleteById(id);
    }

    
    // ========== Búsquedas ==========
    @Transactional(readOnly = true)
    public Page<PersonaResumenResponse> searchPersonas(FiltroPersonasRequest filtro, Pageable pageable) {
        Specification<Persona> spec = buildSpecification(filtro);
        return personaRepository.findAll(spec, pageable)
            .map(persona -> modelMapper.map(persona, PersonaResumenResponse.class));
    }

    private Specification<Persona> buildSpecification(FiltroPersonasRequest filtro) {
        Specification<Persona> spec = Specification.where(null);

        if (filtro.getNombre() != null) {
            spec = spec.and((root, query, cb) ->
                cb.like(cb.lower(root.get("nombre")), "%" + filtro.getNombre().toLowerCase() + "%"));
        }

        if (filtro.getAnioNacimiento() != null) {
            spec = spec.and((root, query, cb) ->
                cb.equal(cb.function("YEAR", Integer.class, root.get("fechaNacimiento")),
                         filtro.getAnioNacimiento()));
        }

        return spec;
    }
    

    // ========== Métodos Utilitarios ==========
    @Transactional(readOnly = true)
    public boolean existsByDni(Integer dni) {
        return personaRepository.existsByDni(dni);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }
}