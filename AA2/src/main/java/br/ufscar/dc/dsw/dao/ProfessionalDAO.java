package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.domain.Professional;

import org.springframework.data.repository.CrudRepository;

//@SuppressWarnings("unchecked")
public interface ProfessionalDAO extends CrudRepository<Professional, String> {
    Professional findByCpf(String cpf);

	Professional findById(long id);

	List<Professional> findAll();

	List<Professional> findByExpertise(String expertise);

    List<Professional> findByKnowledgeArea(String knowledgeArea);
}
