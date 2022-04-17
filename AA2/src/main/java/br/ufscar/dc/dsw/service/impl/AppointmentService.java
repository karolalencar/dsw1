package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.AppointmentDAO;
import br.ufscar.dc.dsw.domain.Appointment;
import br.ufscar.dc.dsw.domain.User;
import br.ufscar.dc.dsw.service.spec.IAppointmentService;

@Service
@Transactional(readOnly = false)
public class AppointmentService implements IAppointmentService {

	@Autowired
	AppointmentDAO dao;
	
	public void salvar(Appointment appointment) {
		dao.save(appointment);
	}
	
	@Transactional(readOnly = true)
	public Appointment buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarTodosPorUsuario(User u) {
		return dao.findAllByUser(u);
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarPorIdCliente(Long client_id) {
		return dao.findByIdClient(client_id);
	}
	
	@Transactional(readOnly = true)
	public List<Appointment> buscarPorIdProfissional(Long professional_id) {
		return dao.findByIdProfessional(professional_id);
	}
}
