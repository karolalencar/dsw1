package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Professional;

public class ProfessionalDAO extends GenericDAO {

    public void insert(Professional professional){
        String sqlUsers = "INSERT INTO users(name, email, cpf, password, role)";
        sqlUsers += " VALUES(?, ?, ?, ?, ?)";
        
        try{
            Connection conn = this.getConnection();
            PreparedStatement statementUsers = conn.prepareStatement(sqlUsers);
            statementUsers = conn.prepareStatement(sqlUsers);
            
            statementUsers.setString(1, professional.getName());
            statementUsers.setString(2, professional.getEmail());
            statementUsers.setString(3, professional.getCpf());
            statementUsers.setString(4, professional.getPassword());
            statementUsers.setString(5, professional.getRole());

            statementUsers.executeUpdate();
            statementUsers.close();
            conn.close();
        } catch (SQLException e){
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }

        String sqlProfessionals = "INSERT INTO professionals(cpf, qualifications, knowledge_area, expertise)";
        sqlProfessionals+= " VALUES(?, ?, ?, ?)";
        try{
            Connection conn = this.getConnection();
            PreparedStatement statementProfessional = conn.prepareStatement(sqlProfessionals);
            statementProfessional = conn.prepareStatement(sqlProfessionals);
            
            statementProfessional.setString(1, professional.getCpf());
            statementProfessional.setString(2, professional.getQualifications());
            statementProfessional.setString(3, professional.getKnowledgeArea());
            statementProfessional.setString(4, professional.getExpertise());
            statementProfessional.executeUpdate();
            
            statementProfessional.close();
            conn.close();
        } catch (SQLException e){
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
    }

    public List<Professional> getAll() {

        List<Professional> professionalList = new ArrayList<>();

        String sql= "SELECT * from professionals INNER JOIN users ON professionals.cpf=users.cpf";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            

            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String qualifications = resultSet.getString("qualifications");
                String knowledge_area = resultSet.getString("knowledge_area");
                String expertise = resultSet.getString("expertise");
                
                Professional professional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
                professionalList.add(professional);

            }
         
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
        return professionalList;
    }

    public List<Professional> getAllByArea(String area) {

        List<Professional> professionalList = new ArrayList<>();

        String sql= "SELECT * from professionals INNER JOIN users ON professionals.cpf=users.cpf WHERE knowledge_area = '"+ area+ "'";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            

            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String qualifications = resultSet.getString("qualifications");
                String knowledge_area = resultSet.getString("knowledge_area");
                String expertise = resultSet.getString("expertise");
                
                Professional professional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
                professionalList.add(professional);

            }
         
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
        return professionalList;
    }

    public List<Professional> getAllByAreaExpertise(String area, String expertise) {

        List<Professional> professionalList = new ArrayList<>();

        String sql= "SELECT * from professionals INNER JOIN users ON professionals.cpf=users.cpf WHERE knowledge_area = '"+ area+ "' AND expertise = '"+ expertise+ "'";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            

            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String qualifications = resultSet.getString("qualifications");
                String knowledge_area = resultSet.getString("knowledge_area");
                
                Professional professional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
                professionalList.add(professional);

            }
         
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
        return professionalList;
    }


    public void delete(Professional professional){
        String sql = "Delete FROM users where cpf = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, professional.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
    }

    public void update(Professional professional){
        String sqlUser = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE cpf = ?";
        String sqlProfessional = "UPDATE professionals SET qualifications = ?, knowledge_area = ?, expertise = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            PreparedStatement statementProfessional = conn.prepareStatement(sqlProfessional);

            statementUser.setString(1, professional.getName());
            statementUser.setString(2, professional.getEmail());
            statementUser.setString(3, professional.getPassword());
            statementUser.setString(4, professional.getRole());
            statementUser.setString(5, professional.getCpf());


            statementProfessional.setString(1, professional.getQualifications());
            statementProfessional.setString(2, professional.getKnowledgeArea());
            statementProfessional.setString(3, professional.getExpertise());
            statementProfessional.setString(4, professional.getCpf());
            
            
            statementUser.executeUpdate();
            statementProfessional.executeUpdate();

            statementUser.close();
            statementProfessional.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
    }

    public Professional get(String cpf) {
        Professional professional = null;
        
        String sqlProfessional = "SELECT * from professionals where cpf = ?";
        String sqlUser = "SELECT * from users where cpf = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statementProfessional = conn.prepareStatement(sqlProfessional);
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementProfessional.setString(1, cpf);
            statementUser.setString(1, cpf);
            ResultSet resultSetProfessional = statementProfessional.executeQuery();
            ResultSet resultSetUser = statementUser.executeQuery();

            // Convertendo resultados para a classe interna Cliente
            if (resultSetProfessional.next()) {
                String qualifications = resultSetProfessional.getString("qualifications");
                String knowledge_area = resultSetProfessional.getString("knowledge_area");
                String expertise = resultSetProfessional.getString("expertise");
                resultSetUser.next();
                String name = resultSetUser.getString("name");
                String email = resultSetUser.getString("email");
                String password = resultSetUser.getString("password");
                String role = resultSetUser.getString("role");
                                    
                
                professional = new Professional(cpf, name, email, password, role, qualifications, knowledge_area, expertise);
            }
           
            resultSetUser.close();
            resultSetProfessional.close();
            statementProfessional.close();
            statementUser.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }
        return professional;
    }

    public Professional getbyLogin(String email) {
        Professional professional = null;
        
        String sqlProfessional = "SELECT * from professionals where cpf = ?";
        String sqlUser = "SELECT * from users where email = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementUser.setString(1, email);
            ResultSet resultSetUser = statementUser.executeQuery();
            String cpf = "#";
            // Convertendo resultados para a classe interna Cliente
            if (resultSetUser.next()) {
                String name = resultSetUser.getString("name");
                cpf = resultSetUser.getString("cpf");
                String password = resultSetUser.getString("password");
                String role = resultSetUser.getString("role");      
                PreparedStatement statementProfessional = conn.prepareStatement(sqlProfessional);
                statementProfessional.setString(1, cpf);
                ResultSet resultSetProfessional = statementProfessional.executeQuery();
                if(resultSetProfessional.next() && cpf != "9" ){
                    String qualifications = resultSetProfessional.getString("qualifications");
                    String knowledge_area = resultSetProfessional.getString("knowledge_area");
                    String expertise = resultSetProfessional.getString("expertise");
                    professional = new Professional( cpf, name, email, password, qualifications, knowledge_area, expertise, role);
                }
                
                resultSetProfessional.close();
                statementProfessional.close();
            }
            

            
            resultSetUser.close();
            statementUser.close();
            conn.close();
        } catch (SQLException e) {
        	System.out.println("ERRO: " + e);
            throw new RuntimeException(e);
        }

        
        return professional;
    }


}