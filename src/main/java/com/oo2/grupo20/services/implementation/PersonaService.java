package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.oo2.grupo20.dto.PersonaDTO;
import com.oo2.grupo20.entities.Persona;
import com.oo2.grupo20.repositories.IPersonaRepository;

@Service ("personaService")
public abstract class PersonaService implements IPersonaRepository{

    private IPersonaRepository personaRepository;
    private ModelMapper modelMapper;

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

    public PersonaDTO getById(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        return modelMapper.map(persona, PersonaDTO.class);
    }

    public PersonaDTO create(PersonaDTO dto) {
        Persona persona = modelMapper.map(dto, Persona.class);
        persona.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt())); // Para aplicar con Spring Security
        return modelMapper.map(personaRepository.save(persona), PersonaDTO.class);
    }

    public PersonaDTO update(PersonaDTO dto) {
        Persona persona = personaRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        modelMapper.map(dto, persona);
        return modelMapper.map(personaRepository.save(persona), PersonaDTO.class);
    }

    public boolean delete(Long id) {
        if (!personaRepository.existsById(id)) return false;
        personaRepository.deleteById(id);
        return true;
    }

    public Optional<Persona> findByUsername(String username) {
        return personaRepository.findByUsername(username);
    }
    
}
