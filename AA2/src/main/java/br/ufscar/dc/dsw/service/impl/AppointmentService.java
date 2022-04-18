package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.AppointmentDAO;
import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.service.spec.IAppointmentService;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Professional;


@Service
@Transactional(readOnly = false)
public class AppointmentService implements IAppointmentService {

	@Autowired
	AppointmentDAO dao;
	
	public void salvar(Appointment appointment) {
		dao.save(appointment);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Appointment buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarTodosPorUsuario() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarPorIdCliente(Client client) {
		return dao.findByClient(client);
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarPorIdProfissional(Professional professional) {
		return dao.findByProfessional(professional);
	}
}
