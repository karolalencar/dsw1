package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.domain.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<Client, String> {
	Client findByCpf(String cpf);
	
	Client findById(long id);
	
	List<Client> findAll();
}
