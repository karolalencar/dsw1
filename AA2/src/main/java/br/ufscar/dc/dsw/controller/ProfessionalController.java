package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.service.spec.IProfessionalService;
import br.ufscar.dc.dsw.service.spec.IUserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/professionals")
public class ProfessionalController {

    @Autowired
	private IUserService userService;

	@Autowired
	private IProfessionalService professionalService;

    @GetMapping("/cadastrar")
	public String cadastrar(Professional professional) {
		return "profissional/cadastro";
	}

    @GetMapping("/listar")
	public String listar(@RequestParam(required = false) String expertise, @RequestParam(required = false) String knowledgeArea, ModelMap model) {
		List<Professional> professionals = professionalService.buscarTodos();
		Set<String> expertiseHash = new HashSet<String>();

        if (knowledgeArea != null && !knowledgeArea.isEmpty()) {
			professionals = professionalService.buscarPorKnowledgeArea(knowledgeArea);
            for (Professional professional : professionals) {
                String expertiseAux = professional.getExpertise();
                if (!expertiseHash.contains(expertiseAux)) {
                    expertiseHash.add(expertiseAux);
                }
            }
		}

		if (expertise != null && !expertise.isEmpty()) {
			professionals = professionalService.buscarPorExpertise(expertise);
		}
		
		model.addAttribute("profissionais", professionals);
		model.addAttribute("expertises", expertiseHash);
		return "lista_profissionais";
	}

    @PostMapping("/salvar")// Salva o profissional no banco em todas as devidas tabelas
	public String salvar(@Valid Professional professional, BindingResult result, RedirectAttributes attr, BCryptPasswordEncoder encoder, @RequestParam("file") MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		professional.setQualifications(file.getBytes());
		professional.setFilename(fileName);

		if (professional.getRole() == null) {
			professional.setRole("PROF");
		}

		/*if (result.hasErrors()) {
			System.out.print("cheguei aqui");
			return "profissional/cadastro";
		}*/

		professional.setPassword(encoder.encode(professional.getPassword()));
		userService.salvar(professional);
		attr.addFlashAttribute("sucess", "Profissional inserido com sucesso");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getAuthorities().toString().equals("[ROLE_ANONYMOUS]")){
			UsuarioDetails user = (UsuarioDetails)authentication.getPrincipal();
			String role = user.getAuthorities().toString();
				
			if(role.equals("[ADMIN]")){
				
				return "redirect:/professionals/listar";
			}
		}

		
		
		return "/login";
	}

    @GetMapping("/editar/{id}") // Recupera o profissional e os atributos dele. Usado pelo admin
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("professional", userService.buscarPorId(id));
		return "profissional/edicao";
	}

    @PostMapping("/editar") // Edita um profissional. Usado pelo admin
	public String editar(@Valid Professional professional, BindingResult result, RedirectAttributes attr,BCryptPasswordEncoder encoder, @RequestParam("file") MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		professional.setQualifications(file.getBytes());
		professional.setFilename(fileName);

		if (professional.getRole() == null) {
			professional.setRole("PROF");
		}

		/*if (result.hasErrors()) {
			return "profissional/edicao";
		}*/

		professional.setPassword(encoder.encode(professional.getPassword()));
		userService.salvar(professional);
		attr.addFlashAttribute("sucess", "Profissional editado com sucesso.");
		return "redirect:/professionals/listar";
	}

    @GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        
		userService.excluir(id);
		attr.addFlashAttribute("sucess", "Profissional excluído com sucesso.");
		
		return "redirect:/professionals/listar";
	}

	@GetMapping(value = "/download/{id}")
	public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) {
		Professional professional= professionalService.buscarPorId(id);

		// set content type
		response.setContentType("application/pdf");
		
		// add response header (caso queira forçar o download)
		//response.addHeader("Content-Disposition", "attachment; filename=" + professional.getName());
		try {
			// copies all bytes to an output stream
			response.getOutputStream().write(professional.getQualifications());
			
			// flushes output stream
			response.getOutputStream().flush();
		} catch (IOException e) {
			System.out.println("Error :- " + e.getMessage());
			System.out.print("Error :- ");
		}
	}

}
