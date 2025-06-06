package com.oo2.grupo20.services.implementation;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oo2.grupo20.dto.request.FiltroPersonasRequest;
import com.oo2.grupo20.dto.request.PersonaRequest;
import com.oo2.grupo20.dto.response.PersonaResponse;
import com.oo2.grupo20.dto.response.PersonaResumenResponse;
import com.oo2.grupo20.entities.Persona;
import com.oo2.grupo20.exceptions.DuplicatePersonaException;
import com.oo2.grupo20.exceptions.PersonaNotFoundException;
import com.oo2.grupo20.repositories.IPersonaRepository;

@Service ("personaService")
public class PersonaService implements IPersonaRepository{

    private IPersonaRepository personaRepository;
    private ModelMapper modelMapper;

   
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

    
    @Transactional(readOnly = true)
    public Page<PersonaResumenResponse> searchPersonas(FiltroPersonasRequest filtro, Pageable pageable) {
        return personaRepository.findAll(pageable)
            .map(persona -> modelMapper.map(persona, PersonaResumenResponse.class));
    }

    @Transactional(readOnly = true)
    public boolean existsByDni(Integer dni) {
        return personaRepository.existsByDni(dni);
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return personaRepository.existsByEmail(email);
    }

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Persona> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Persona> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Serializable> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persona getOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getReferenceById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> findAllById(Iterable<Serializable> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Persona> findById(Serializable id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Persona entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Serializable> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Persona> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Persona> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Persona> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Persona> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Persona> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Persona, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Persona> findByDni(Integer dni) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Persona> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Persona> findByAnioNacimiento(int anio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Persona> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Persona> findByNombreIgnoreCase(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}
