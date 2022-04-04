package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Appointment;

import org.springframework.data.repository.CrudRepository;

@SuppressWarnings("unchecked")
public interface AppointmentDAO extends CrudRepository<Appointment, String> {

}
