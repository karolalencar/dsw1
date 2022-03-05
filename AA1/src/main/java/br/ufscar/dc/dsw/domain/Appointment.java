package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Appointment {
    private String cpfCliente;
    private String cpfProfissional;
    private LocalDate dataConsulta;
    private Integer horaConsulta;

    public Appointment(String cpfCliente, String cpfProfissional, LocalDate dataConsulta, Integer horaConsulta) {
        this.setCpfCliente(cpfCliente);
        this.setCpfProfissional(cpfProfissional);
        this.setDataConsulta(dataConsulta);
        this.setHoraConsulta(horaConsulta);
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfCliente() {
        return this.cpfCliente;
    }

    public void setCpfProfissional(String cpfProfissional) {
        this.cpfProfissional = cpfProfissional;
    }

    public String getCpfProfissional() {
        return this.cpfProfissional;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalDate getDataConsulta() {
        return this.dataConsulta;
    }

    public void setHoraConsulta(Integer horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public Integer getHoraConsulta() {
        return this.horaConsulta;
    }
}