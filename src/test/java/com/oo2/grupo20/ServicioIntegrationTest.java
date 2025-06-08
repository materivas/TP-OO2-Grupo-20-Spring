package com.oo2.grupo20;

import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.dto.ServicioDTO;
import com.oo2.grupo20.repositories.IEstablecimientoRepository;
import com.oo2.grupo20.services.IServicioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ServicioIntegrationTest {

    @Autowired
    private IServicioService servicioService;

    @Autowired
    private IEstablecimientoRepository establecimientoRepository;

    @Test
    void testCrearServiciosYEstablecimientos() {
        // PRIMER ESTABLECIMIENTO
        Establecimiento est1 = new Establecimiento();
        est1.setNombre("Clínica del Sur");
        est1.setLocalidad("Avenida Siempreviva 742");
        Establecimiento est1Guardado = establecimientoRepository.save(est1);
        assertNotNull(est1Guardado.getIdEstablecimiento());

        // SEGUNDO ESTABLECIMIENTO
        Establecimiento est2 = new Establecimiento();
        est2.setNombre("Sanatorio Central");
        est2.setLocalidad("Boulevard Salud 100");
        Establecimiento est2Guardado = establecimientoRepository.save(est2);
        assertNotNull(est2Guardado.getIdEstablecimiento());

        // PRIMER SERVICIO
        Servicio serv1 = new Servicio();
        serv1.setNombreServicio("Pediatría");
        serv1.setDescripcion("Atención médica para niños");
        serv1.setDuracion(30);
        serv1.setPrecio(1200.0);
        serv1.setHoraInicio(LocalTime.of(8, 0));
        serv1.setHoraFin(LocalTime.of(14, 0));
        serv1.setEstablecimiento(est1Guardado);

        Servicio serv1Guardado = servicioService.insertOrUpdate(serv1);
        assertNotNull(serv1Guardado.getIdServicio());

        // SEGUNDO SERVICIO
        Servicio serv2 = new Servicio();
        serv2.setNombreServicio("Traumatología");
        serv2.setDescripcion("Consultas de lesiones óseas");
        serv2.setDuracion(60);
        serv2.setPrecio(2000.0);
        serv2.setHoraInicio(LocalTime.of(10, 0));
        serv2.setHoraFin(LocalTime.of(18, 0));
        serv2.setEstablecimiento(est2Guardado);

        Servicio serv2Guardado = servicioService.insertOrUpdate(serv2);
        assertNotNull(serv2Guardado.getIdServicio());

        // VERIFICACIÓN SERVICIO 1
        Optional<ServicioDTO> dto1 = servicioService.findByIdWithEstablecimiento(serv1Guardado.getIdServicio());
        assertTrue(dto1.isPresent());
        assertEquals("Pediatría", dto1.get().getNombreServicio());
        assertEquals("Clínica del Sur", dto1.get().getEstablecimiento().getNombre());
        assertEquals("Avenida Siempreviva 742", dto1.get().getEstablecimiento().getLocalidad());

        // VERIFICACIÓN SERVICIO 2
        Optional<ServicioDTO> dto2 = servicioService.findByIdWithEstablecimiento(serv2Guardado.getIdServicio());
        assertTrue(dto2.isPresent());
        assertEquals("Traumatología", dto2.get().getNombreServicio());
        assertEquals("Sanatorio Central", dto2.get().getEstablecimiento().getNombre());
        assertEquals("Boulevard Salud 100", dto2.get().getEstablecimiento().getLocalidad());
    }
}
