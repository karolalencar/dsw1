package br.ufscar.dc.dsw.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="Admin")
public class Administrator extends User {

    @NotBlank
	@Size(min = 14, max = 14)
	@Column(nullable = false, unique = true, length = 60)
	private String cpf;

    public String getCpf() {
		return cpf;
	}

	public void setCpf(String Cpf) {
		this.cpf = Cpf;
	}
}