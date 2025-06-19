package com.oo2.grupo20.controllers;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Empleado;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.IClienteService;
import com.oo2.grupo20.services.IDiaService;
import com.oo2.grupo20.services.IEmpleadoService;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.services.ITurnoService;
import com.oo2.grupo20.services.implementation.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/turno")
public class TurnoController {

    private final ITurnoService turnoService;
    private final IClienteService clienteService;
    private final IEmpleadoService empleadoService;
    private final IServicioService servicioService;
    private final IDiaService diaService;
    private final EmailService emailService;
    
    
    @GetMapping("/index")
    public String listarTurnos(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Es el DNI o email

        // Determinar el rol
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean isEmpleado = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_EMPLEADO"));
        boolean isCliente = auth.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_CLIENTE"));

        List<Turno> turnos;

        if (isAdmin) {
            // Admin ve todos los turnos
            turnos = turnoService.findAll();
        } else if (isEmpleado) {
            // Empleado ve turnos asignados a él
            Empleado empleado = empleadoService.findByEmail(username); // o findByDni si usás DNI
            if (empleado == null) {
                model.addAttribute("error", "Empleado no encontrado");
                return ViewRouteHelper.TURNO_INDEX;
            }
            turnos = turnoService.findByEmpleadoDni(empleado.getDni());
        } else if (isCliente) {
            // Cliente ve sus propios turnos
            Cliente cliente = clienteService.findByEmail(username); // o findByDni si usás DNI
            if (cliente == null) {
                model.addAttribute("error", "Cliente no encontrado");
                return ViewRouteHelper.TURNO_INDEX;
            }
            turnos = turnoService.findByClienteDni(cliente.getDni());
        } else {
            // Por seguridad, no muestra nada si no se identifica correctamente
            turnos = List.of();
        }

        model.addAttribute("turnos", turnos);
        return ViewRouteHelper.TURNO_INDEX;
    }


    private void cargarDatosModelo(Model model) {
        model.addAttribute("clientes", clienteService.getAll());
        model.addAttribute("empleados", empleadoService.getAll());
        model.addAttribute("servicios", servicioService.getAll());
    }
    
    @GetMapping("/nuevo")
    public String nuevoTurno(Model model) {
        Turno turno = new Turno();
        turno.setCliente(new Cliente());    
        turno.setEmpleado(new Empleado());  
        turno.setServicio(new Servicio()); 
        turno.setDia(new Dia());            
        model.addAttribute("turno", turno);

        cargarDatosModelo(model);

        return "turno/form"; 
    }

    @PostMapping("/guardar")
    public String guardarTurno(@Valid @ModelAttribute("turno") Turno turno, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        // 1. Cargar datos necesarios para el formulario (si hay errores)
        cargarDatosModelo(model);

        // 2. Validación manual de fecha futura
        if (turno.getDia() == null || turno.getDia().getFecha() == null) {
            result.rejectValue("dia.fecha", "error.fecha", "La fecha es requerida");
        } else if (turno.getDia().getFecha().isBefore(LocalDate.now())) {
            result.rejectValue("dia.fecha", "error.fecha", "La fecha debe ser futura");
        }

        // 3. Validación manual de hora
        if (turno.getHora() == null) {
            result.rejectValue("hora", "error.hora", "La hora es requerida");
        } else if (turno.getHora().isBefore(LocalTime.of(8, 0))) {
            result.rejectValue("hora", "error.hora", "La hora mínima es 08:00");
        } else if (turno.getHora().isAfter(LocalTime.of(18, 0))) {
            result.rejectValue("hora", "error.hora", "La hora máxima es 18:00");
        }

        // 4. Si hay errores, volver al formulario
        if (result.hasErrors()) {
            return "turno/form";
        }

        try {
            // 5. Validación y persistencia del día
            if (turno.getServicio() == null) {
                throw new IllegalArgumentException("El servicio es requerido");
            }
            Dia diaPersistido = diaService.findOrCreateByFechaAndServicio(
                turno.getDia().getFecha(),
                turno.getServicio()
            );
            turno.setDia(diaPersistido);

            // 6. Completa las entidades relacionadas
            turno.setCliente(clienteService.getClienteEntityById(turno.getCliente().getId()));
            turno.setServicio(servicioService.getServicioEntityById(turno.getServicio().getIdServicio()));

            // 7. Guardar el turno (con validaciones internas)
            turnoService.save(turno);

            // 8. Enviar email
            emailService.enviarEmailHtml(
            	    turno.getCliente().getEmail(),
            	    "Confirmación de Turno",
            	    turno.getCliente().getNombre(),
            	    turno.getDia().getFecha().toString(),
            	    turno.getHora().toString(),
            	    turno.getServicio().getNombreServicio()
            	);

            redirectAttributes.addFlashAttribute("mensajeExito", 
                "¡Turno registrado! Se envió un correo a " + turno.getCliente().getEmail());

        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            return "turno/form";
        }

        return "redirect:/turno/index";
    }

    
    @PostMapping("/eliminar/{id}")
    public String eliminarTurno(@PathVariable("id") Long id) {
        turnoService.deleteById(id);
        return "redirect:/turno/index";
    }

    //Editar turno existente
    @GetMapping("/editar/{id}")
    public String editarTurno(@PathVariable("id") Long id, Model model) {
        Turno turno = turnoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        model.addAttribute("turno", turno);
        cargarDatosModelo(model);
        return ViewRouteHelper.TURNO_FORM;
    }

    // Ver detalle de un turno (opcional)
    @GetMapping("/detalle/{id}")
    public String detalleTurno(@PathVariable("id") Long id, Model model) {
        Turno turno = turnoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        model.addAttribute("turno", turno);
        return ViewRouteHelper.TURNO_DETAIL;
    }
 
}
