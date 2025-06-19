package com.oo2.grupo20.controllers;

import com.oo2.grupo20.entities.*;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.*;
import com.oo2.grupo20.services.implementation.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String username = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean isEmpleado = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_EMPLEADO"));
        boolean isCliente = auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_CLIENTE"));

        List<Turno> turnos;
        if (isAdmin) {
            turnos = turnoService.findAll();
        } else if (isEmpleado) {
            Empleado empleado = empleadoService.findByEmail(username);
            turnos = (empleado != null) ? turnoService.findByEmpleadoDni(empleado.getDni()) : List.of();
        } else if (isCliente) {
            Cliente cliente = clienteService.findByEmail(username);
            turnos = (cliente != null) ? turnoService.findByClienteDni(cliente.getDni()) : List.of();
        } else {
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

    // Paso 1: Elegir servicio
    @GetMapping("/seleccionar-servicio")
    public String seleccionarServicio(Model model) {
        model.addAttribute("servicios", servicioService.getAll());
        return "turno/seleccionar-servicio";
    }

    // Paso 2: Formulario de turno con días disponibles
    @GetMapping("/nuevo")
    public String nuevoTurno(@RequestParam(name = "servicioId", required = false) Long servicioId,
                             @RequestParam(name = "diaFecha", required = false) String diaFechaStr,
                             Model model) {
        if (servicioId == null) {
            return "redirect:/turno/seleccionar-servicio";
        }

        Turno turno = new Turno();
        turno.setCliente(new Cliente());
        turno.setEmpleado(new Empleado());
        turno.setServicio(new Servicio());
        turno.setDia(new Dia());

        model.addAttribute("turno", turno);
        cargarDatosModelo(model);
        model.addAttribute("servicioId", servicioId);
        model.addAttribute("diasDisponibles", diaService.findFechasDisponiblesPorServicio(servicioId));

        
        // Si llegó una fecha seleccionada (diaFecha):
        // 1. La convierto a LocalDate.
        // 2. Traigo el servicio con ese servicioId.
        // 3. Busco o creo el día para ese servicio y fecha.
        // 4. Calculo las horas disponibles ese día para ese servicio.
        // 5. Paso esas horas y la fecha seleccionada al modelo para el formulario.

        if (diaFechaStr != null && !diaFechaStr.isEmpty()) {
            LocalDate fecha = LocalDate.parse(diaFechaStr);
            Servicio servicio = servicioService.getServicioEntityById(servicioId);
            Dia dia = diaService.findOrCreateByFechaAndServicio(fecha, servicio);
            List<LocalTime> horasDisponibles = turnoService.generarHorasDisponibles(servicio, dia);
            model.addAttribute("horasDisponibles", horasDisponibles);
            model.addAttribute("diaSeleccionada", fecha);
        }

        return "turno/form";
    }

    @PostMapping("/guardar")
    public String guardarTurno(@Valid @ModelAttribute("turno") Turno turno,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes,
                                @RequestParam(value = "diaFecha", required = false) String diaFechaStr) {

        cargarDatosModelo(model);

        if (diaFechaStr == null || diaFechaStr.isEmpty()) {
            model.addAttribute("error", "Debe seleccionar una fecha disponible");
            return "turno/form";
        }

        LocalDate fechaSeleccionada;
        try {
            fechaSeleccionada = LocalDate.parse(diaFechaStr);
        } catch (Exception e) {
            model.addAttribute("error", "Fecha inválida");
            return "turno/form";
        }

        if (fechaSeleccionada.isBefore(LocalDate.now())) {
            model.addAttribute("error", "La fecha debe ser futura");
            return "turno/form";
        }

        Servicio servicio = servicioService.getServicioEntityById(turno.getServicio().getIdServicio());
        Dia diaPersistido = diaService.findOrCreateByFechaAndServicio(fechaSeleccionada, servicio);
        turno.setDia(diaPersistido);

        if (turno.getHora() == null) {
            result.rejectValue("hora", "error.hora", "La hora es requerida");
        } else if (turno.getHora().isBefore(LocalTime.of(8, 0))) {
            result.rejectValue("hora", "error.hora", "La hora mínima es 08:00");
        } else if (turno.getHora().isAfter(LocalTime.of(18, 0))) {
            result.rejectValue("hora", "error.hora", "La hora máxima es 18:00");
        }

        if (result.hasErrors()) {
            return "turno/form";
        }

        turno.setCliente(clienteService.getClienteEntityById(turno.getCliente().getId()));
        turno.setServicio(servicio);

        try {
            turnoService.save(turno);
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

    @GetMapping("/editar/{id}")
    public String editarTurno(@PathVariable("id") Long id, Model model) {
        Turno turno = turnoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        model.addAttribute("turno", turno);
        cargarDatosModelo(model);
        return ViewRouteHelper.TURNO_FORM;
    }

    @GetMapping("/detalle/{id}")
    public String detalleTurno(@PathVariable("id") Long id, Model model) {
        Turno turno = turnoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Turno no encontrado"));
        model.addAttribute("turno", turno);
        return ViewRouteHelper.TURNO_DETAIL;
    }
}
