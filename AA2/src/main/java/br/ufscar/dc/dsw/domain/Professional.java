package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="Professional")
public class Professional extends User {
    
	@NotBlank
	@Size(min = 11, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
    private String email;
	
	@NotBlank
	@Column(nullable = false, length = 100)
	private String qualifications;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String knowledgeArea;

    @NotBlank
	@Column(nullable = false, length = 50)
	private String expertise;

	@OneToMany(mappedBy = "professional")
	private List<Appointment> appointments;
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String Cpf) {
		this.cpf = Cpf;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}