package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="Professional")
public class Professional extends User {
    
	@NotBlank
	@Size(min = 14, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;
	
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

	public String getQualification() {
		return qualifications;
	}

	public void setQualification(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getKnowledge_area() {
		return knowledgeArea;
	}

	public void setKnowledge_area(String knowledge_area) {
		this.knowledgeArea = knowledge_area;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
}