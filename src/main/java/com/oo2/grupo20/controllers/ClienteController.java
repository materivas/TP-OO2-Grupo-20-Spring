package com.oo2.grupo20.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.oo2.grupo20.entities.Cliente;
import com.oo2.grupo20.entities.Dia;
import com.oo2.grupo20.entities.Servicio;
import com.oo2.grupo20.entities.Turno;
import com.oo2.grupo20.dto.ClienteDTO;
import com.oo2.grupo20.dto.TurnoFormDTO;
import com.oo2.grupo20.helpers.ViewRouteHelper;
import com.oo2.grupo20.services.IClienteService;
import com.oo2.grupo20.services.IDiaService;
import com.oo2.grupo20.services.IServicioService;
import com.oo2.grupo20.services.ITurnoService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    private final IClienteService clienteService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ITurnoService turnoService;
    private final IServicioService servicioService;
    private final IDiaService diaService;
    
    public ClienteController(
            IClienteService clienteService,
            IServicioService servicioService,
            IDiaService diaService,
            ITurnoService turnoService
        ) {
            this.clienteService = clienteService;
            this.servicioService = servicioService;
            this.diaService = diaService;
            this.turnoService = turnoService;
        }   
    
    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
        List<ClienteDTO> clientesDTO = clienteService.getAll().stream()
                .map(c -> modelMapper.map(c, ClienteDTO.class))
                .collect(Collectors.toList());
        mAV.addObject("clientes", clientesDTO);
        return mAV;
    }
    
    //Creación de clientes
    @GetMapping("/new")
    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_FORM);
        mAV.addObject("cliente", new ClienteDTO());
        return mAV;
    }
    
    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("cliente") ClienteDTO clienteDTO) {
        System.out.println("Contraseña recibida desde el formulario: " + clienteDTO.getPassword());

        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        System.out.println("Contraseña en entidad Cliente (después del mapping): " + cliente.getPassword());

        cliente.setFechaRegistro(LocalDate.now());
        
        clienteService.insertOrUpdate(cliente);
        return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
    }

    
    // Búsquedas específicas
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") long id) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_UPDATE);
        ClienteDTO clienteDTO = modelMapper.map(clienteService.getAll().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null), ClienteDTO.class);
        mAV.addObject("cliente", clienteDTO);
        return mAV;
    }

    @GetMapping("/by_dni/{dni}")
    public ModelAndView getByDni(@PathVariable("dni") Integer dni) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_DETAIL);
        clienteService.findByDni(dni).ifPresent(clienteDTO -> 
            mAV.addObject("cliente", clienteDTO));
        return mAV;
    }
    
    @GetMapping("/by_apellido/{apellido}")
    public ModelAndView getByApellido(@PathVariable("apellido") String apellido) {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENTE_INDEX);
        mAV.addObject("clientes", clienteService.findByApellido(apellido));
        return mAV;
    }
    
    @GetMapping("/editar/{dni}")
    public String editForm(@PathVariable Integer dni, Model model) {
        Optional<ClienteDTO> clienteOpt = clienteService.findByDni(dni);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            return "cliente/form"; 
        } else {
            // Se redirige a la lista con un mensaje o a una página de error
            return "redirect:/cliente/index"; 
        }
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("cliente") ClienteDTO clienteDTO) {
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteService.insertOrUpdate(cliente);
        return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
    }
    
    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") long id) {
        clienteService.remove(id);
        return new RedirectView(ViewRouteHelper.CLIENTE_ROOT);
    }
    
    //GET para sacar turno mediante un forms
    @GetMapping("/clientes/{id}/sacar-turno")
    public String mostrarFormularioTurno(@PathVariable("id") Long clienteId, Model model) {
        Cliente cliente = clienteService.getClienteEntityById(clienteId); 
        List<Servicio> serviciosDisponibles = servicioService.getAll(); 

        model.addAttribute("cliente", cliente);
        model.addAttribute("servicios", serviciosDisponibles);
        model.addAttribute("turnoForm", new TurnoFormDTO());
        
        return ViewRouteHelper.CLIENTE_TURNO_FORM;
    }
    
    @PostMapping("/clientes/{id}/sacar-turno")
    public String procesarTurno(@PathVariable Long id, @ModelAttribute("turnoForm") TurnoFormDTO form) {

        Cliente cliente = clienteService.getClienteEntityById(id);
        Servicio servicio = servicioService.getServicioEntityById(form.getServicioId());

        // Lógica para obtener/crear el Día
        Dia dia = diaService.findOrCreateByFecha(form.getFecha());


        Turno turno = new Turno();
        turno.setCliente(cliente);
        turno.setServicio(servicio);
        turno.setDia(dia);

        turnoService.save(turno);

        return "redirect:/clientes/" + id + "/detalle";
    }


    
}