package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.impl.AppointmentService;
import br.ufscar.dc.dsw.service.impl.ClientService;
import br.ufscar.dc.dsw.service.impl.ProfessionalService;

@Controller
@RequestMapping("/consultas")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ProfessionalService professionalService;
	
	private boolean horarioDisponivel(Appointment appointment) {
		List<Appointment> appointments = appointmentService.buscarPorIdProfissional(appointment.getId());
		
		for (int i = 0; i < appointments.size(); i++) {
			if (appointments.get(i).getDate().equals(appointment.getDate()) && appointments.get(i).getHour() == appointment.getHour()) {
				return true;
			}
		}
		
		appointments = appointmentService.buscarPorIdCliente(appointment.getId());
		
		for (int i = 0; i < appointments.size(); i++) {
			if (appointments.get(i).getDate().contentEquals(appointment.getDate()) && appointments.get(i).getHour() == appointment.getHour()) {
				return true;
			}
		}
		
		return false;
	}
	
	private User getUser() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}
	
	@GetMapping("/agendar/{cpfProf}")
	public String preAgendar(@PathVariable("cpfProf") String cpfProf, ModelMap model) {
		return "consulta/agendamento";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Appointment appointment, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/";
		}
		
		if (! horarioDisponivel(appointment)) {
			appointmentService.salvar(appointment);
			attr.addFlashAttribute("sucess", "Consulta agendada com sucesso");
			return "redirect:/";
		} else {
			attr.addFlashAttribute("fail", "Não foi possível agendar a consulta.");
			return "redirect:/professionals/listar";
		}
	}
}
