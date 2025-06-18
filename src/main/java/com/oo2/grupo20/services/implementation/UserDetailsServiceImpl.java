package com.oo2.grupo20.services.implementation;

import com.oo2.grupo20.entities.Persona;
import com.oo2.grupo20.entities.UserDetailsImpl;
import com.oo2.grupo20.repositories.IPersonaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IPersonaRepository personaRepository;

    public UserDetailsServiceImpl(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuario con email/username: " + username);
        Persona persona = personaRepository.findByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("Usuario no encontrado con email: " + username);
                    return new UsernameNotFoundException("Usuario no encontrado");
                });
        
        System.out.println("Usuario encontrado: " + persona.getEmail() + 
                          ", Estado: " + persona.isEstado() + 
                          ", Rol: " + persona.getRol() +
                          ", Password: " + persona.getPassword());
        
        return new UserDetailsImpl(persona);
    }

}
