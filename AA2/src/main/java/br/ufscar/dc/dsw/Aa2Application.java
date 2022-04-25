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

			byte[] ba2 = {1,2,3,4,5};
			Professional professional1 = new Professional();
			professional1.setUsername("professional1");
			professional1.setName("Aquila Oliveira");
			professional1.setPassword(encoder.encode("oi"));
			professional1.setRole("PROF");
			professional1.setCpf("50324053826");
			professional1.setEmail("aquila@estudante.ufscar.br");
			professional1.setExpertise("Terapia Cognitiva");
			professional1.setKnowledgeArea("Engenharias");
			professional1.setQualifications(ba2);
			professional1.setEnabled(true);
			professional1.setFilename("teste");
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
			professional2.setEnabled(true);
			professional2.setFilename("teste");
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
			//professional3.setQualifications("teste");
			professional3.setEnabled(true);
			professional3.setFilename("teste");
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
			//professional4.setQualifications("teste");
			professional4.setEnabled(true);
			professional4.setFilename("teste");
			usuarioDAO.save(professional4);
			
			Client client1 = new Client();
			client1.setUsername("client1");
			client1.setName("Kiko");
			client1.setPassword(encoder.encode("abc"));
			client1.setRole("CLIENT");
			client1.setCpf("00000000000000");
			client1.setEmail("superaquila00@gmail.com");
			client1.setTelephone("99999999999");
			client1.setGender("M");
			client1.setBirthDate("2010-03-10");
			client1.setEnabled(true);
			usuarioDAO.save(client1);
			
			Client client2 = new Client();
			client2.setUsername("client2");
			client2.setName("Cliente");
			client2.setPassword(encoder.encode("abcd"));
			client2.setRole("CLIENT");
			client2.setCpf("00000000000001");
			client2.setEmail("client2@gmail.com");
			client2.setTelephone("99999999998");
			client2.setGender("F");
			client2.setBirthDate("2000-03-10");
			client2.setEnabled(true);
			usuarioDAO.save(client2);
		};
	}

}
