package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Professional;


public interface IAppointmentService {

	Appointment buscarPorId(Long id);
	
	void salvar(Appointment appointment);
	
	List<Appointment> buscarTodosPorUsuario();
	
	List<Appointment> buscarPorIdCliente(Client client);
	
	List<Appointment> buscarPorIdProfissional(Professional professional);
}
