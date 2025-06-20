package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.dto.ClienteDTO;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.repositories.IClienteRepository;
import com.oo2.grupo20.services.IClienteService;

@Service("clienteService")
public class ClienteService implements IClienteService {

    private IClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    
    private ModelMapper modelMapper = new ModelMapper ();
    
    
    
    
    public ClienteService(IClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        System.out.println("PasswordEncoder inyectado? " + (passwordEncoder != null));
    }
    
    
    @Override
    public Cliente insertOrUpdate(Cliente cliente) {
    	if (cliente.getId() == null) {
    	    System.out.println("Contraseña antes de encriptar: " + cliente.getPassword());
    	    cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
    	    System.out.println("Contraseña después de encriptar: " + cliente.getPassword());
    	}


        cliente.setRol(Rol.CLIENTE); // Asignar rol
        cliente.setEstado(true);
        return clienteRepository.save(cliente);
    }

    
    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public boolean remove(long idCliente) {
        try {
            clienteRepository.deleteById(idCliente);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    
    @Override
    public Optional<ClienteDTO> findByDni(Integer dni) {
        return clienteRepository.findByDni(dni)
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class));
    }
    
    @Override
    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con email: " + email));
    }

    @Override
    public List<ClienteDTO> findByApellido(String apellido) {
        return clienteRepository.findByApellido(apellido).stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> findByNombreAndApellido(String nombre, String apellido) {
        return clienteRepository.findByNombreAndApellido(nombre, apellido).stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByDni(Integer dni) {
        return clienteRepository.existsByDni(dni);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    //Añadidas excepciones debido al Runtime 
    @Override
    public ClienteDTO getById(long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado")); 
        return modelMapper.map(cliente, ClienteDTO.class);
    }
    
    @Override
    public Cliente getClienteEntityById(long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }



}