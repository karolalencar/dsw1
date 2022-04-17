package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.User;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface AppointmentDAO extends CrudRepository<Appointment, Long> {

	Appointment findById(long id);
	
	Appointment save(Appointment appointment);
	
	List<Appointment> findAllByUser(User u);
	
	List<Appointment> findByIdClient(Long client_id);
	
	List <Appointment> findByIdProfessional(Long professional_id);
}
