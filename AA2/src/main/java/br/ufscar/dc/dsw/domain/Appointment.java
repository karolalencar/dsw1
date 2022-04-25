package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Appointment")
public class Appointment extends AbstractEntity<Long> {
    
    @ManyToOne
    @JoinColumn(name = "client_id")
	private Client client;

    @ManyToOne
    @JoinColumn(name = "professional_id")
	private Professional professional;

    @NotBlank
	@Size(min = 8, max = 12)
	@Column(nullable = false, unique = false, length = 60)
	private String dateAppointment;
    
	@Column(nullable = false, unique = false)
	private int hourAppointment;

    public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

    public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

    public String getDateAppointment() {
		return dateAppointment;
	}

	public void setDateAppointment(String date) {
		this.dateAppointment = date;
	}

    public int getHourAppointment() {
		return hourAppointment;
	}

	public void setHourAppointment(int hour) {
		this.hourAppointment = hour;
	}
}
