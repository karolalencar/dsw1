package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Client {
    private String cpf;
    private String name;
    private String email;
    private String password;
    private String gender;
    private String telephone;
    private LocalDate birthDate;
    private String role;


    public Client(String cpf) {
        this.setCpf(cpf);
    }

    public Client(String cpf, String name, String email, String password, String gender, String telephone, LocalDate birthDate, String role) {
        this.setCpf(cpf);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setGender(gender);
        this.setTelephone(telephone);
        this.setBirthDate(birthDate);
        this.setRole(role);
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

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}