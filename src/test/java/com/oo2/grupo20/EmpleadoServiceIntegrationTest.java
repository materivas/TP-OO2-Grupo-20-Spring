package com.oo2.grupo20;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Especialidad;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.repositories.IEspecialidadRepository;
import com.oo2.grupo20.repositories.IEstablecimientoRepository;
import com.oo2.grupo20.services.IEmpleadoService;
import com.oo2.grupo20.dto.EmpleadoDTO;
import com.oo2.grupo20.dto.EmpleadoConEspecialidadesYEstablecimientoDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmpleadoServiceIntegrationTest {

  // USAMOS AUTOWIRED UNICAMENTE PARA CORRER LOS TESTS
  @Autowired
  private IEmpleadoService empleadoService;

  @Autowired
  private IEspecialidadRepository especialidadRepository;

  @Autowired
  private IEstablecimientoRepository establecimientoRepository;

  @Autowired
  PasswordEncoder encoder;

  @Test
  void testInsertarYBuscarEmpleadosConRelaciones() {

    // CREAMOS LAS ESPECIALIDADES
    Especialidad especialidad1 = new Especialidad();
    especialidad1.setNombre("Neurología");
    especialidad1 = especialidadRepository.save(especialidad1);

    Especialidad especialidad2 = new Especialidad();
    especialidad2.setNombre("Traumatología");
    especialidad2 = especialidadRepository.save(especialidad2);

    // CREAMOS LOS ESTABLECIMIENTOS
    Establecimiento establecimiento1 = new Establecimiento();
    establecimiento1.setNombre("Hospital General");
    establecimiento1.setLocalidad("Calle Falsa 123");
    establecimiento1 = establecimientoRepository.save(establecimiento1);

    Establecimiento establecimiento2 = new Establecimiento();
    establecimiento2.setNombre("Clínica del Sur");
    establecimiento2.setLocalidad("Avenida Siempre Viva 456");
    establecimiento2 = establecimientoRepository.save(establecimiento2);

    // EMPLEADO 1
    Empleado empleado1 = new Empleado();
    empleado1.setNombre("Carlos");
    empleado1.setApellido("García");
    empleado1.setDni(32165498);
    empleado1.setUsername("CarlosGarcia12");
    empleado1.setEmail("carlos.garcia@test.com");
    empleado1.setPassword("1234");
    empleado1.setRol(Rol.EMPLEADO);
    empleado1.setEstado(true);
    empleado1.setFechaDeNacimiento(LocalDate.of(1985, 5, 20));
    empleado1.setCUIL("20303030303");

    empleado1.setEstablecimiento(establecimiento1);

    Set<Especialidad> especialidades1 = new HashSet<>();
    especialidades1.add(especialidad1);
    empleado1.setEspecialidades(especialidades1);

    Empleado empleadoGuardado1 = empleadoService.insertOrUpdate(empleado1);
    assertNotNull(empleadoGuardado1.getId());

    // UTILIZAMOS ESTE DTO DE EMPLEADO QUE NOS TRAE SUS ATRIBUTOS JUNTO CON LAS
    // ESPECIALIDADES Y ESTABLECIMIENTOS ASOCIADOS
    Optional<EmpleadoConEspecialidadesYEstablecimientoDTO> resultado1 = empleadoService
        .findByIdWithEspecialidadesAndEstablecimiento2(empleadoGuardado1.getId());
    assertTrue(resultado1.isPresent());

    /*
     * VERIFICAMOS QUE LOS DATOS SE HAYAN CARGADO CORRECTAMENTE CON assertEquals,
     * QUE TIRA UNA EXCEPCION (NULL POINTER EXCEPTION) SI ENCUENTRA UN VALOR NULO
     */
    EmpleadoConEspecialidadesYEstablecimientoDTO dto1 = resultado1.get();
    assertEquals("Carlos", dto1.getNombre());
    assertEquals("García", dto1.getApellido());
    assertEquals("20303030303", dto1.getCUIL());
    assertNotNull(dto1.getEstablecimiento());
    assertEquals("Hospital General", dto1.getEstablecimiento().getNombre());
    assertEquals(1, dto1.getEspecialidades().size());
    assertTrue(dto1.getEspecialidades().stream().anyMatch(e -> e.getNombre().equals("Neurología")));

    // EMPLEADO 2
    Empleado empleado2 = new Empleado();
    empleado2.setNombre("Lucía");
    empleado2.setApellido("Ramírez");
    empleado2.setUsername("LuRamirez");
    empleado2.setPassword("1234");
    empleado1.setRol(Rol.EMPLEADO);
    empleado2.setCUIL("20404040404");
    empleado2.setDni(40251879);
    empleado2.setEmail("lucia.ramirez@test.com");
    empleado2.setFechaDeNacimiento(LocalDate.of(1990, 8, 15));
    empleado2.setEstado(true);

    empleado2.setEstablecimiento(establecimiento2);

    Set<Especialidad> especialidades2 = new HashSet<>();
    especialidades2.add(especialidad2);
    empleado2.setEspecialidades(especialidades2);

    Empleado empleadoGuardado2 = empleadoService.insertOrUpdate(empleado2);
    assertNotNull(empleadoGuardado2.getId());

    // UTILIZAMOS ESTE DTO DE EMPLEADO QUE NOS TRAE SUS ATRIBUTOS JUNTO CON LAS
    // ESPECIALIDADES Y ESTABLECIMIENTOS ASOCIADOS
    Optional<EmpleadoConEspecialidadesYEstablecimientoDTO> resultado2 = empleadoService
        .findByIdWithEspecialidadesAndEstablecimiento2(empleadoGuardado2.getId());
    assertTrue(resultado2.isPresent());

    /*
     * VERIFICAMOS QUE LOS DATOS SE HAYAN CARGADO CORRECTAMENTE CON assertEquals,
     * QUE TIRA UNA EXCEPCION (NULL POINTER EXCEPTION) SI ENCUENTRA UN VALOR NULO
     */
    EmpleadoConEspecialidadesYEstablecimientoDTO dto2 = resultado2.get();
    assertEquals("Lucía", dto2.getNombre());
    assertEquals("Ramírez", dto2.getApellido());
    assertEquals("20404040404", dto2.getCUIL());
    assertNotNull(dto2.getEstablecimiento());
    assertEquals("Clínica del Sur", dto2.getEstablecimiento().getNombre());
    assertEquals(1, dto2.getEspecialidades().size());
    assertTrue(dto2.getEspecialidades().stream().anyMatch(e -> e.getNombre().equals("Traumatología")));
  }
}
