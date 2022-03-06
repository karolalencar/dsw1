package br.ufscar.dc.dsw.domain;

public class Professional {
    private String cpf;
    private String name;
    private String email;
    private String password;
    private String role;
    private String qualifications;
    private String knowledge_area;
    private String expertise;

    public Professional(String cpf) {
        this.setCpf(cpf);
    }

    public Professional(String cpf, String name, String email, String password, String role, String qualifications, String knowledge_area, String expertise) {
        this.setCpf(cpf);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
        this.setQualifications(qualifications);
        this.setKnowledgeArea(knowledge_area);
        this.setExpertise(expertise);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
    
    public void setQualifications(String qualifications) {
    	this.qualifications = qualifications;
    }
    
    public String getQualifications() {
    	return this.qualifications;
    }
    
    public void setKnowledgeArea(String knowledge_area) {
    	this.knowledge_area = knowledge_area;
    }
    
    public String getKnowledgeArea() {
    	return this.knowledge_area;
    }
    
    public void setExpertise(String expertise) {
    	this.expertise = expertise;
    }
    
    public String getExpertise() {
    	return this.expertise;
    }
}