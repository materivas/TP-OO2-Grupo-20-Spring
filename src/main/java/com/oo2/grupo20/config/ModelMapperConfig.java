package com.oo2.grupo20.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oo2.grupo20.dto.EstablecimientoBasicDTO;
import com.oo2.grupo20.entities.Establecimiento;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
    	 ModelMapper modelMapper = new ModelMapper(); 
    	 
    	 modelMapper.getConfiguration()
    	 	.setSkipNullEnabled(true)
    	 	.setAmbiguityIgnored(true);
    	    
    	 /*
    	// Mapeo manual: Establecimiento -> EstablecimientoBasicDTO
         TypeMap<Establecimiento, EstablecimientoBasicDTO> typeMap = modelMapper.createTypeMap(
             Establecimiento.class, EstablecimientoBasicDTO.class
         );

         typeMap.addMappings(mapper -> {
             mapper.map(Establecimiento::getIdEstablecimiento, EstablecimientoBasicDTO::setIdEstablecimiento);
             mapper.map(Establecimiento::getNombre, EstablecimientoBasicDTO::setNombre);
             mapper.map(Establecimiento::getLocalidad, EstablecimientoBasicDTO::setLocalidad);
         });
*/
         return modelMapper;
    	 
    	 
    	
    }
}