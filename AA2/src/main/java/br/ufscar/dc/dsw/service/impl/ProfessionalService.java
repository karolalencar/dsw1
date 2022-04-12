package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ProfessionalDAO;
import br.ufscar.dc.dsw.domain.Professional;
import br.ufscar.dc.dsw.service.spec.IProfessionalService;

@Service
@Transactional(readOnly = false)
public class ProfessionalService implements IProfessionalService{

    @Autowired
	ProfessionalDAO dao;

    @Transactional(readOnly = true)
	public Professional buscarPorCpf(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly = true)
	public Professional buscarPorId(Long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Professional> buscarTodos() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public List<Professional> buscarPorExpertise(String expertise) {
		return dao.findByExpertise(expertise);
	}

    @Transactional(readOnly = true)
	public List<Professional> buscarPorKnowledgeArea(String knowledgeArea) {
		return dao.findByKnowledgeArea(knowledgeArea);
	}
	
}
