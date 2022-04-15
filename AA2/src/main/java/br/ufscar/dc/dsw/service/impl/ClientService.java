package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ClientDAO;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.service.spec.IClientService;

@Service
@Transactional(readOnly = false)
public class ClientService implements IClientService {

	@Autowired
	ClientDAO dao;
	
	@Transactional(readOnly = true)
	public Client buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}
	
	@Transactional(readOnly = true)
	public Client buscarPorId(Long id) {
		return dao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Client> buscarTodos() {
		return dao.findAll();
	}
}
