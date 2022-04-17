package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.User;

public interface IAppointmentService {

	Appointment buscarPorId(Long id);
	
	void salvar(Appointment appointment);
	
	List<Appointment> buscarTodosPorUsuario(User u);
	
	List<Appointment> buscarPorIdCliente(Long client_id);
	
//	List<Appointment> buscarPorIdProfissional(Long professional_id);
}
