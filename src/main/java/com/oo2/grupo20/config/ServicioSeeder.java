package com.oo2.grupo20.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.services.implementation.ServicioService;
import com.oo2.grupo20.repositories.IEstablecimientoRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
public class ServicioSeeder {

	@Bean
	@Order(1)
	CommandLineRunner initServicios(ServicioService servicioService, IEstablecimientoRepository establecimientoRepo) {
	    return args -> {

	        // Si ya hay servicios cargados, no hacemos nada
	        if (!servicioService.getAll().isEmpty()) {
	            return;
	        }

	        // Crear establecimientos si no existen
	        if (establecimientoRepo.count() == 0) {
	            Establecimiento e1 = new Establecimiento();
	            e1.setNombre("Sanatorio Central");
	            e1.setLocalidad("Córdoba");

	            Establecimiento e2 = new Establecimiento();
	            e2.setNombre("Clínica del Sur");
	            e2.setLocalidad("Rosario");

	            Establecimiento e3 = new Establecimiento();
	            e3.setNombre("Hospital Norte");
	            e3.setLocalidad("Buenos Aires");

	            establecimientoRepo.saveAll(List.of(e1, e2, e3));
	        }

	        var establecimientos = establecimientoRepo.findAll();

	        for (Establecimiento est : establecimientos) {
	            // Neurología
	            Servicio neurology = new Servicio();
	            neurology.setNombreServicio("Neurología (" + est.getNombre() + ")");
	            neurology.setDescripcion("Servicio de Neurología");
	            neurology.setDuracion(60);
	            neurology.setHoraInicio(LocalTime.of(8, 0));
	            neurology.setHoraFin(LocalTime.of(16, 0));
	            neurology.setPrecio(2500.0);
	            neurology.setEstablecimiento(est);
	            neurology.setDiasDisponibles(new HashSet<>(Arrays.asList(
	                    DayOfWeek.MONDAY,
	                    DayOfWeek.WEDNESDAY,
	                    DayOfWeek.FRIDAY
	            )));
	            servicioService.insertOrUpdate(neurology);

	            // Cardiología
	            Servicio cardiology = new Servicio();
	            cardiology.setNombreServicio("Cardiología (" + est.getNombre() + ")");
	            cardiology.setDescripcion("Servicio de Cardiología");
	            cardiology.setDuracion(30);
	            cardiology.setHoraInicio(LocalTime.of(9, 0));
	            cardiology.setHoraFin(LocalTime.of(15, 0));
	            cardiology.setPrecio(3000.0);
	            cardiology.setEstablecimiento(est);
	            cardiology.setDiasDisponibles(new HashSet<>(Arrays.asList(
	                    DayOfWeek.TUESDAY,
	                    DayOfWeek.THURSDAY
	            )));
	            servicioService.insertOrUpdate(cardiology);

	            // Traumatología
	            Servicio traumatology = new Servicio();
	            traumatology.setNombreServicio("Traumatología (" + est.getNombre() + ")");
	            traumatology.setDescripcion("Servicio de Traumatología");
	            traumatology.setDuracion(60);
	            traumatology.setHoraInicio(LocalTime.of(10, 0));
	            traumatology.setHoraFin(LocalTime.of(17, 0));
	            traumatology.setPrecio(2800.0);
	            traumatology.setEstablecimiento(est);
	            traumatology.setDiasDisponibles(new HashSet<>(Arrays.asList(
	                    DayOfWeek.MONDAY,
	                    DayOfWeek.THURSDAY,
	                    DayOfWeek.FRIDAY
	            )));
	            servicioService.insertOrUpdate(traumatology);
	        }
	    };
	}

}
