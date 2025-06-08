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

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    
    @GetMapping("/index")
    public String listarTurnos(Model model) {
        List<Turno> turnos = turnoService.findAll();
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
    public String guardarTurno(@Valid @ModelAttribute("turno") Turno turno, BindingResult result, Model model) {
        
        if (result.hasErrors()) {
            cargarDatosModelo(model);
            return "turno/form";
        }

        try {
            // Validación adicional
            if (turno.getServicio() == null) {
                throw new IllegalArgumentException("El servicio es requerido");
            }

            Dia diaPersistido = diaService.findOrCreateByFechaAndServicio(
                turno.getDia().getFecha(),
                turno.getServicio()
            );
            
            turno.setDia(diaPersistido);
            turnoService.save(turno);
            
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar: " + e.getMessage());
            cargarDatosModelo(model);
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
        model.addAttribute("clientes", clienteService.getAll());
        model.addAttribute("empleados", empleadoService.getAll());
        model.addAttribute("servicios", servicioService.getAll());
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
