package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import br.ufscar.dc.dsw.domain.Appointment;

public class AppointmentDAO extends GenericDAO {

    public void insert(Appointment appointment){

        String sqlAppointment = "INSERT INTO appointments(cpf_cliente, cpf_professional, data_consulta, hora_consulta)";
        sqlAppointment += " VALUES(?, ?, ?, ?)";


        // Inserindo o cliente primeiro na table user
        try{
            Connection conn = this.getConnection();
            PreparedStatement statementAppointment = conn.prepareStatement(sqlAppointment);
            statementAppointment = conn.prepareStatement(sqlAppointment);
            
            statementAppointment.setString(1, appointment.getCpfCliente());
            statementAppointment.setString(2, appointment.getCpfProfissional());
            statementAppointment.setDate(3, java.sql.Date.valueOf(appointment.getDataConsulta()));
            statementAppointment.setInt(4, appointment.getHoraConsulta());
    
            statementAppointment.executeUpdate();

            statementAppointment.close();
            conn.close();
            
        } catch (SQLException e){
            throw new RuntimeException(e); 
        }
    }

    public List<Appointment> getAllByClient(String cpf_cliente) {

        List<Appointment> appointmentList = new ArrayList<>();

        String sql= "SELECT * from appointments where cpf_cliente = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statementAppointment = conn.prepareStatement(sql);
            statementAppointment = conn.prepareStatement(sql);
            statementAppointment.setString(1, cpf_cliente);

            ResultSet resultSet = statementAppointment.executeQuery();
            
            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpfCliente = resultSet.getString("cpf_cliente");
                String cpfProfissional = resultSet.getString("cpf_professional");
                LocalDate dataConsulta = LocalDate.parse(resultSet.getDate("data_consulta").toString());
                Integer horaConsulta = resultSet.getInt("hora_consulta");
                
                Appointment appointment = new Appointment(cpfCliente, cpfProfissional, dataConsulta, horaConsulta);
                appointmentList.add(appointment);

            }
         
            resultSet.close();
            statementAppointment.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public List<Appointment> getAllByProfessional(String cpf_professional) {

        List<Appointment> appointmentList = new ArrayList<>();

        String sql= "SELECT * from appointments where cpf_professional = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statementAppointment = conn.prepareStatement(sql);
            statementAppointment = conn.prepareStatement(sql);
            statementAppointment.setString(1, cpf_professional);

            ResultSet resultSet = statementAppointment.executeQuery();
            

            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpfCliente = resultSet.getString("cpf_cliente");
                String cpfProfissional = resultSet.getString("cpf_professional");
                LocalDate dataConsulta = LocalDate.parse(resultSet.getDate("data_consulta").toString());
                Integer horaConsulta = resultSet.getInt("hora_consulta");
                
                Appointment appointment = new Appointment(cpfCliente, cpfProfissional, dataConsulta, horaConsulta);
                appointmentList.add(appointment);

            }
         
            resultSet.close();
            statementAppointment.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public void delete(Appointment appointment){
        String sql = "Delete FROM appointments where cpf_cliente = ? AND cpf_professional = ? AND data_consulta = ? AND hora_consulta = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, appointment.getCpfCliente());
            statement.setString(2, appointment.getCpfProfissional());
            statement.setDate(3, java.sql.Date.valueOf(appointment.getDataConsulta()));
            statement.setInt(4, appointment.getHoraConsulta());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}