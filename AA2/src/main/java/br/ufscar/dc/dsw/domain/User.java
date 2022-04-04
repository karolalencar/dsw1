package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
//import javax.persistence.Id;
import javax.persistence.Table;

//import br.ufscar.dsw.profissionais.domain.AbstractEntity;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends AbstractEntity<Long> {
 
    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false, length = 64)
    private String password;
    
    @Column(nullable = false, length = 45)
    private String role;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

    public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

    public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	

}