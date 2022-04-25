package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Client")
public class Client extends User {

    @NotBlank
	@Size(min = 11, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
    private String email;

    @NotBlank
	@Size(min = 11, max = 15)
	@Column(nullable = false, unique = false, length = 60)
	private String telephone;

    @NotBlank
	@Size(min = 1, max = 60)
	@Column(nullable = false, unique = false, length = 60)
	private String gender;

    @NotBlank
	@Size(min = 8, max = 10)
	@Column(nullable = false, unique = false, length = 60)
	private String birthDate;

	@OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = false)
	private List<Appointment> appointments;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
    public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

    public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}
