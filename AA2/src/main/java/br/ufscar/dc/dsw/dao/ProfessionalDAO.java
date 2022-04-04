package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Professional;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface ProfessionalDAO extends CrudRepository<Professional, String> {

}
