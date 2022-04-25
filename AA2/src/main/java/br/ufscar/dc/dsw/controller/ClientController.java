package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.service.spec.IClientService;
import br.ufscar.dc.dsw.service.spec.IUserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/clientes")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@Autowired 
	private IUserService userService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Client client) {

		return "cliente/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		List<Client> clients = clientService.buscarTodos();
		model.addAttribute("clientes", clients);
		return "/cliente/lista";
		
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Client client, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {

		if (client.getRole() == null) {
			client.setRole("CLIENT");
		}

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}
		
		client.setPassword(encoder.encode(client.getPassword()));
		userService.salvar(client);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")){
			UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
			String role = user.getAuthorities().toString();
				
			if(role.equals("[ADMIN]")){

				return "redirect:/clientes/listar";
			}
		}
		

		return "/login";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Client client, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder) {
		if (client.getRole() == null) {
			client.setRole("CLIENT");
		}
		
		if (result.hasErrors()) {
			return "cliente/edicao";
		}
		
		client.setPassword(encoder.encode(client.getPassword()));
		userService.salvar(client);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("client", userService.buscarPorId(id));
		return "cliente/edicao";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		userService.excluir(id);
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		
		return "redirect:/clientes/listar";
	}
}
