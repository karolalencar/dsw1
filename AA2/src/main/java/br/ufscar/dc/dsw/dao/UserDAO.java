package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.User;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface UserDAO extends CrudRepository<User, String> {

}
