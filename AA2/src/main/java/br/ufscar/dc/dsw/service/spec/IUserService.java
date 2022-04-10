package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.User;

public interface IUserService {

    User buscarPorId(Long id);

	User buscarPorCpf(String Cpf);

	List<User> buscarTodos();

	void salvar(User user);

	void excluir(Long id);
}
