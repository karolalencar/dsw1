package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Professional;

public interface IProfessionalService {

	Professional buscarPorCpf(String cpf);

	Professional buscarPorId(Long id);

	List<Professional> buscarTodos();

	List<Professional> buscarPorExpertise(String expertise);

    List<Professional> buscarPorKnowledgeArea(String knowledgeArea);

}