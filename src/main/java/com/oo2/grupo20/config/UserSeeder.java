package com.oo2.grupo20.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Rol;
import com.oo2.grupo20.repositories.IEmpleadoRepository;
import com.oo2.grupo20.repositories.IClienteRepository;
import com.oo2.grupo20.repositories.IPersonaRepository;

import java.time.LocalDate;

@Configuration
public class UserSeeder {

    @Bean
    CommandLineRunner initUsers(IEmpleadoRepository empleadoRepo, IClienteRepository clienteRepo, PasswordEncoder encoder) {
        return args -> {

            if (!empleadoRepo.existsByEmail("empleado@example.com")) {
                Empleado emp = new Empleado();
                emp.setNombre("Juan");
                emp.setApellido("Pérez");
                emp.setDni(11111111);
                emp.setUsername("empleado");
                emp.setEmail("empleado@example.com");
                emp.setPassword(encoder.encode("1234"));
                emp.setRol(Rol.EMPLEADO);
                emp.setEstado(false);
                emp.setFechaDeNacimiento(LocalDate.of(1990, 5, 10));
                emp.setCUIL("20-11111111-1");
                empleadoRepo.save(emp);
            }
            
            if (!clienteRepo.existsByEmail("cliente2@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Aston");
                cli.setApellido("Martin");
                cli.setDni(55555555);
                cli.setUsername("cliente2");
                cli.setEmail("cliente2@example.com");
                cli.setPassword(encoder.encode("12345"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(false);
                cli.setFechaDeNacimiento(LocalDate.of(1995, 8, 20));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }

            if (!clienteRepo.existsByEmail("cliente@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Ana");
                cli.setApellido("Gómez");
                cli.setDni(22222222);
                cli.setUsername("cliente");
                cli.setEmail("cliente@example.com");
                cli.setPassword(encoder.encode("1234"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(false);
                cli.setFechaDeNacimiento(LocalDate.of(1995, 8, 20));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }
            

            if (!empleadoRepo.existsByEmail("admin@example.com")) {
                Empleado admin = new Empleado();
                admin.setNombre("Admin");
                admin.setApellido("Principal");
                admin.setDni(33333333);
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRol(Rol.ADMIN);
                admin.setEstado(false);
                admin.setFechaDeNacimiento(LocalDate.of(1985, 1, 1));
                admin.setCUIL("20-33333333-1");
                empleadoRepo.save(admin);
            }
            
            
            if (!clienteRepo.existsByEmail("cliente3@example.com")) {
                Cliente cli = new Cliente();
                cli.setNombre("Juanito");
                cli.setApellido("Perez");
                cli.setDni(44444444);
                cli.setUsername("cliente3");
                cli.setEmail("cliente3@example.com");
                cli.setPassword(encoder.encode("123456"));
                cli.setRol(Rol.CLIENTE);
                cli.setEstado(true);
                cli.setFechaDeNacimiento(LocalDate.of(1995, 8, 20));
                cli.setFechaRegistro(LocalDate.now());
                clienteRepo.save(cli);
            }
            
            
        };
    }
}
