package br.ufscar.dc.dsw.dao;

import java.util.List;

import br.ufscar.dc.dsw.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@SuppressWarnings("unchecked")
public interface UserDAO extends CrudRepository<User, String> {

	/*@Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);*/

	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);

    User findById(long id);

	User findByCpf(String cpf);

	List<User> findAll();
	
	User save(User user);

	void deleteById(Long id);
}
