package com.oo2.grupo20.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Establecimiento;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.repositories.IEmpleadoRepository;
import com.oo2.grupo20.repositories.IClienteRepository;
import com.oo2.grupo20.repositories.IEstablecimientoRepository;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UserSeeder {

    @Bean
    @Order(2)
    CommandLineRunner initUsers(IEmpleadoRepository empleadoRepo, IClienteRepository clienteRepo, IEstablecimientoRepository establecimientoRepo, PasswordEncoder encoder) {
        return args -> {

            List<Establecimiento> establecimientos = establecimientoRepo.findAll();

            if (establecimientos.size() < 3) {
                System.err.println(" No hay suficientes establecimientos en la base de datos para asignar a los empleados.");
                return;
            }

            if (!empleadoRepo.existsByEmail("empleado1@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Juan");
                emp.setApellido("Pérez");
                emp.setDni(11111111);
                emp.setUsername("empleado1");
                emp.setEmail("empleado1@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(true);
                emp.setFechaDeNacimiento(LocalDate.of(1990, 5, 10));
                emp.setCUIL("20-11111111-1");
                emp.setEstablecimiento(establecimientos.get(0));
                empleadoRepo.save(emp);
            }

            if (!empleadoRepo.existsByEmail("empleado2@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Lucía");
                emp.setApellido("Martínez");
                emp.setDni(12222222);
                emp.setUsername("empleado2");
                emp.setEmail("empleado2@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(true);
                emp.setFechaDeNacimiento(LocalDate.of(1991, 6, 15));
                emp.setCUIL("20-12222222-1");
                emp.setEstablecimiento(establecimientos.get(1));
                empleadoRepo.save(emp);
            }

            if (!empleadoRepo.existsByEmail("empleado3@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Carlos");
                emp.setApellido("García");
                emp.setDni(13333333);
                emp.setUsername("empleado3");
                emp.setEmail("empleado3@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(true);
                emp.setFechaDeNacimiento(LocalDate.of(1988, 2, 20));
                emp.setCUIL("20-13333333-1");
                emp.setEstablecimiento(establecimientos.get(2));
                empleadoRepo.save(emp);
            }

            if (!empleadoRepo.existsByEmail("empleado4@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Sofía");
                emp.setApellido("Fernández");
                emp.setDni(14444444);
                emp.setUsername("empleado4");
                emp.setEmail("empleado4@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(true);
                emp.setFechaDeNacimiento(LocalDate.of(1993, 12, 5));
                emp.setCUIL("20-14444444-1");
                emp.setEstablecimiento(establecimientos.get(0));
                empleadoRepo.save(emp);
            }

            if (!empleadoRepo.existsByEmail("empleado5@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Miguel");
                emp.setApellido("López");
                emp.setDni(15555555);
                emp.setUsername("empleado5");
                emp.setEmail("empleado5@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(true);
                emp.setFechaDeNacimiento(LocalDate.of(1992, 3, 8));
                emp.setCUIL("20-15555555-1");
                emp.setEstablecimiento(establecimientos.get(1));
                empleadoRepo.save(emp);
            }

            if (!empleadoRepo.existsByEmail("admin@example.com")) {
                Empleado admin = new Empleado();
                admin.setNombre("Admin");
                admin.setApellido("Principal");
                admin.setDni(16666666);
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol(Rol.ADMIN);
                admin.setEstado(true);
                admin.setFechaDeNacimiento(LocalDate.of(1980, 1, 1));
                admin.setCUIL("20-16666666-1");
                empleadoRepo.save(admin);
            }

            if (!clienteRepo.existsByEmail("cliente1@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Ana");
                cli.setApellido("Gómez");
                cli.setDni(21111111);
                cli.setUsername("cliente1");
                cli.setEmail("cliente1@example.com");
                cli.setPassword(encoder.encode("1234"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1995, 8, 20));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

            if (!clienteRepo.existsByEmail("cliente2@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Aston");
                cli.setApellido("Martin");
                cli.setDni(22222222);
                cli.setUsername("cliente2");
                cli.setEmail("cliente2@example.com");
                cli.setPassword(encoder.encode("12345"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1996, 4, 18));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

            if (!clienteRepo.existsByEmail("cliente3@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Juanito");
                cli.setApellido("Pérez");
                cli.setDni(23333333);
                cli.setUsername("cliente3");
                cli.setEmail("cliente3@example.com");
                cli.setPassword(encoder.encode("123456"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1997, 11, 10));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

            if (!clienteRepo.existsByEmail("cliente4@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Laura");
                cli.setApellido("Ruiz");
                cli.setDni(24444444);
                cli.setUsername("cliente4");
                cli.setEmail("cliente4@example.com");
                cli.setPassword(encoder.encode("1234"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1994, 7, 5));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

            if (!clienteRepo.existsByEmail("cliente5@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Diego");
                cli.setApellido("Silva");
                cli.setDni(25555555);
                cli.setUsername("cliente5");
                cli.setEmail("cliente5@example.com");
                cli.setPassword(encoder.encode("1234"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1993, 9, 9));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

        };
    }
}
