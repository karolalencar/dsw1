package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.dao.*;

@SpringBootApplication
public class Aa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Aa2Application.class, args);
	}


	@Bean
	public CommandLineRunner demo(UserDAO usuarioDAO, ProfessionalDAO locadoraDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
						
			User user = new User();
			user.setUsername("admin");
			user.setName("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole("ADMIN");
			user.setEnabled(true);
			usuarioDAO.save(user);


			Professional professional1 = new Professional();
			professional1.setUsername("professional");
			professional1.setName("Aquila Oliveira");
			professional1.setPassword(encoder.encode("teste"));
			professional1.setRole("PROF");
			professional1.setCpf("50004053826");
			professional1.setEmail("profissional1@gmail.com");
			professional1.setExpertise("Terapia Cognitiva");
			professional1.setKnowledgeArea("Saúde");
			professional1.setQualifications("teste");
			professional1.setEnabled(true);
			usuarioDAO.save(professional1);
			
		};
	}

}
