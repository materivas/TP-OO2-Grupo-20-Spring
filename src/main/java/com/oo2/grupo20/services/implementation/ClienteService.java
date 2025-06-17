package com.oo2.grupo20.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.oo2.grupo20.dto.ClienteDTO;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.repositories.IClienteRepository;
import com.oo2.grupo20.services.IClienteService;

@Service("clienteService")
public class ClienteService implements IClienteService {

    private IClienteRepository clienteRepository;
    private ModelMapper modelMapper = new ModelMapper ();

    public ClienteService (IClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

       
    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente insertOrUpdate(Cliente cliente) {
        return clienteRepository.save(cliente);
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
    public Optional<ClienteDTO> findByDni(Integer dni) {
        return clienteRepository.findByDni(dni)
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class));
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