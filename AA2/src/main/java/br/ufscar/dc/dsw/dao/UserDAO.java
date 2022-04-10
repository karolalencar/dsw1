package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.domain.User;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface UserDAO extends CrudRepository<User, String> {

    User findById(long id);

	User findByCpf(String cpf);

	List<User> findAll();
	
	User save(User user);

	void deleteById(Long id);
}
