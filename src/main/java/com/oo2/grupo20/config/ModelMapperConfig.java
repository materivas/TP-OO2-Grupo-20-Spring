package com.oo2.grupo20.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
    	 ModelMapper modelMapper = new ModelMapper(); 
    	 
    	 modelMapper.getConfiguration()
    	 	.setSkipNullEnabled(true)
    	 	.setAmbiguityIgnored(true);
    	    
    	return modelMapper;
    }
}