package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.impl.AppointmentService;
import br.ufscar.dc.dsw.service.spec.IClientService;
import br.ufscar.dc.dsw.service.spec.IProfessionalService;
import br.ufscar.dc.dsw.service.impl.ProfessionalService;
import org.springframework.security.core.Authentication;
import br.ufscar.dc.dsw.service.spec.IAppointmentService;


import br.ufscar.dc.dsw.service.spec.IUserService;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Controller
@RequestMapping("/consultas")
public class AppointmentController {
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Autowired
	private IAppointmentService appointmentService1;

    @Autowired
	private IUserService userService;

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private IClientService clientService;
	@Autowired
	private IProfessionalService profService;
	
	@Autowired
	private ProfessionalService professionalService;

	private Client getClientAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return clientService.buscarPorId(user.getId());
	}

	private Professional getProfAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
		return profService.buscarPorId(user.getId());
	}
	


	void sendEmailClient(Appointment appointment, UUID uuid) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appointment.getClient().getEmail());
        msg.setSubject("Consulta Marcada");
        msg.setText("Olá "+ appointment.getClient().getName() +", sua consulta foi marcada com o profissional "+ appointment.getProfessional().getName()+" as "+ appointment.getHourAppointment()+"h do dia "+appointment.getDateAppointment()+".\n O link para a consulta é: https://meet.google.com/"+ uuid);

        javaMailSender.send(msg);

    }

	void sendEmailProf(Appointment appointment, UUID uuid) {
		
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(appointment.getProfessional().getEmail());
        msg.setSubject("Consulta Marcada");
        msg.setText("Olá "+ appointment.getProfessional().getName() +", uma consulta foi marcada com o cliente "+ appointment.getClient().getName()+" as "+ appointment.getHourAppointment()+"h do dia "+appointment.getDateAppointment()+".\n O link para a consulta é: https://meet.google.com/"+ uuid);

        javaMailSender.send(msg);

    }
	
	private boolean horarioDisponivel(Appointment appointment) {
		List<Appointment> appointments = appointmentService.buscarPorIdProfissional(appointment.getProfessional());
		
		for (int i = 0; i < appointments.size(); i++) {
			if (appointments.get(i).getDateAppointment().equals(appointment.getDateAppointment()) && appointments.get(i).getHourAppointment() == appointment.getHourAppointment()) {
				return true;
			}
		}
		
		appointments = appointmentService.buscarPorIdCliente(appointment.getClient());
		
		for (int i = 0; i < appointments.size(); i++) {
			if (appointments.get(i).getDateAppointment().contentEquals(appointment.getDateAppointment()) && appointments.get(i).getHourAppointment() == appointment.getHourAppointment()) {
				return true;
			}
		}
		
		return false;
	}
	
	@GetMapping("/listarClient")
	public String listarClient(@RequestParam(required = false) String c, ModelMap model) {
		Client client = getClientAutenticado();
		List<Appointment> appointments = appointmentService1.buscarPorIdCliente(client);

		model.addAttribute("consultas", appointments);
		return "consulta/lista";
	}
	
	@GetMapping("/listarProf")
	public String listarProf(@RequestParam(required = false) String c, ModelMap model) {
		Professional prof = getProfAutenticado();
		List<Appointment> appointments = appointmentService1.buscarPorIdProfissional(prof);

		model.addAttribute("consultas", appointments);
		return "consulta/lista";
	}

	@GetMapping("/agendar/{id}")
	public String preAgendar(@PathVariable("id") Long id, Appointment appointment, ModelMap model) {
		model.addAttribute("professional", userService.buscarPorId(id));

		return "consulta/agendamento";
	}

	
	@PostMapping("/salvar")
	public String salvar(@Valid Appointment appointment, BindingResult result, RedirectAttributes attr, Long idProfessional) {
		/*if (result.hasErrors()) {
			System.out.print("PASSEI AKI UASDFHAFSDHUFASDUHFASDUHASFDUHAS\n");
			return "/";
		}*/
		Professional professional = professionalService.buscarPorId(idProfessional);
		appointment.setProfessional(professional);
		appointment.setClient(getClientAutenticado());
		if (! horarioDisponivel(appointment)) {
			UUID uuid = UUID.randomUUID();
			sendEmailClient(appointment, uuid);
			sendEmailProf(appointment, uuid);
			appointmentService1.salvar(appointment);
			attr.addFlashAttribute("sucess", "Consulta agendada com sucesso");
			return "redirect:/consultas/listarClient";
		} else {
			attr.addFlashAttribute("fail", "Não foi possível agendar a consulta.");
			return "redirect:/professionals/listar";
		}

	}
}
