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
			professional1.setUsername("professional1");
			professional1.setName("Aquila Oliveira");
			professional1.setPassword(encoder.encode("oi"));
			professional1.setRole("PROF");
			professional1.setCpf("50324053826");
			professional1.setEmail("profissional1@gmail.com");
			professional1.setExpertise("Terapia Cognitiva");
			professional1.setKnowledgeArea("Engenharias");
			professional1.setQualifications("teste");
			professional1.setEnabled(true);
			usuarioDAO.save(professional1);

			Professional professional2 = new Professional();
			professional2.setUsername("professional2");
			professional2.setName("Robsvaldo");
			professional2.setPassword(encoder.encode("oi"));
			professional2.setRole("PROF");
			professional2.setCpf("50004053809");
			professional2.setEmail("profissional2@gmail.com");
			professional2.setExpertise("Biologia e Espaço Tridimensional");
			professional2.setKnowledgeArea("Ciências Biológicas");
			professional2.setQualifications("teste");
			professional2.setEnabled(true);
			usuarioDAO.save(professional2);
			
			Professional professional3 = new Professional();
			professional3.setUsername("professional3");
			professional3.setName("Vini");
			professional3.setPassword(encoder.encode("oi"));
			professional3.setRole("PROF");
			professional3.setCpf("50004053821");
			professional3.setEmail("profissional3@gmail.com");
			professional3.setExpertise("Biologia Computacional");
			professional3.setKnowledgeArea("Ciências Biológicas");
			professional3.setQualifications("teste");
			professional3.setEnabled(true);
			usuarioDAO.save(professional3);

			Professional professional4 = new Professional();
			professional4.setUsername("professional4");
			professional4.setName("Santiago");
			professional4.setPassword(encoder.encode("oi"));
			professional4.setRole("PROF");
			professional4.setCpf("50004012312");
			professional4.setEmail("profissional4@gmail.com");
			professional4.setExpertise("Lingua Inglesa");
			professional4.setKnowledgeArea("Linguística, Letras e Artes");
			professional4.setQualifications("teste");
			professional4.setEnabled(true);
			usuarioDAO.save(professional4);
			
		};
	}

}
