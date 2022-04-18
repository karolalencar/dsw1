package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Professional;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface AppointmentDAO extends CrudRepository<Appointment, Long > {

	Appointment findById(long id);
	
	List<Appointment> findAll();
	
	List<Appointment> findByClient(Client client);
	
	List <Appointment> findByProfessional(Professional professional);

	Appointment save(Appointment appointment);

	void deleteById(Long id);
}
