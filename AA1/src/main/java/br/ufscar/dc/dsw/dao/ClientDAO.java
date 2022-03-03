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

import br.ufscar.dc.dsw.domain.Client;

public class ClientDAO extends GenericDAO {

    public void insert(Client client){

        String sqlUsers = "INSERT INTO users(cpf, name, email, password, role)";
        sqlUsers += " VALUES(?, ?, ?, ?, ?)";

        // Inserindo o cliente primeiro na table user
        try{
            Connection conn = this.getConnection();
            PreparedStatement statementUsers = conn.prepareStatement(sqlUsers);
            statementUsers = conn.prepareStatement(sqlUsers);
            
            statementUsers.setString(1, client.getCpf());
            statementUsers.setString(2, client.getName());
            statementUsers.setString(3, client.getEmail());
            statementUsers.setString(4, client.getPassword());
            statementUsers.setString(5, client.getRole());
    
            statementUsers.executeUpdate();

            statementUsers.close();
            conn.close();
            
        } catch (SQLException e){
            throw new RuntimeException(e);
            
        }

        // Agora inserindo na tabela clients
        String sqlClients = "INSERT INTO clients(cpf, gender, telephone, birth_date)";
        sqlClients+= " VALUES(?, ?, ?, ?)";
        try{
            Connection conn = this.getConnection();
            PreparedStatement statementClient = conn.prepareStatement(sqlClients);
            statementClient = conn.prepareStatement(sqlClients);

            statementClient.setString(1, client.getCpf());
            statementClient.setString(2, client.getGender());
            statementClient.setString(3, client.getTelephone());
            statementClient.setDate(4, java.sql.Date.valueOf(client.getBirthDate()));
            statementClient.executeUpdate();

            statementClient.close();
            conn.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAll() {

        List<Client> clientList = new ArrayList<>();

        String sql= "SELECT * from clients INNER JOIN users ON clients.cpf=users.cpf";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            

            // Convertendo resultados para a classe interna Cliente
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String telephone = resultSet.getString("telephone");
                String gender= resultSet.getString("gender");
                LocalDate birth_date = LocalDate.parse(resultSet.getDate("birth_date").toString());
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                
                Client client= new Client(cpf, name, email, password, gender, telephone, birth_date, role);
                clientList.add(client);

            }
         
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }

    public void delete(Client client){
        String sql = "Delete FROM users where cpf = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, client.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Client client){
        String sqlUser = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE cpf = ?";
        String sqlClient = "UPDATE clients SET gender = ?, telephone = ?, birth_date = ? WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            PreparedStatement statementClient = conn.prepareStatement(sqlClient);

            statementUser.setString(1, client.getName());
            statementUser.setString(2, client.getEmail());
            statementUser.setString(3, client.getPassword());
            statementUser.setString(4, client.getRole());
            statementUser.setString(5, client.getCpf());


            statementClient.setString(1, client.getGender());
            statementClient.setString(2, client.getTelephone());
            statementClient.setDate(3, java.sql.Date.valueOf(client.getBirthDate()));
            statementClient.setString(4, client.getCpf());
            
            
            statementUser.executeUpdate();
            statementClient.executeUpdate();

            statementUser.close();
            statementClient.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client get(String cpf) {
        Client client = null;
        
        String sqlClient = "SELECT * from clients where cpf = ?";
        String sqlUser = "SELECT * from users where cpf = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statementClient = conn.prepareStatement(sqlClient);
            PreparedStatement statementUser = conn.prepareStatement(sqlUser);
            statementClient.setString(1, cpf);
            statementUser.setString(1, cpf);
            ResultSet resultSetClient = statementClient.executeQuery();
            ResultSet resultSetUser = statementUser.executeQuery();

            // Convertendo resultados para a classe interna Cliente
            if (resultSetClient.next()) {
                String telephone = resultSetClient.getString("telephone");
                String gender = resultSetClient.getString("gender");
                LocalDate birth_date = LocalDate.parse(resultSetClient.getDate("birth_date").toString());
                resultSetUser.next();
                String name = resultSetUser.getString("name");
                String email = resultSetUser.getString("email");
                String password = resultSetUser.getString("password");
                String role = resultSetUser.getString("role");
                                    
                
                client = new Client(cpf, name, email, password, gender, telephone, birth_date, role);
            }
           
            resultSetUser.close();
            resultSetClient.close();
            statementClient.close();
            statementUser.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public Client getbyLogin(String email) {
        Client client = null;
        
        String sqlClient = "SELECT * from clients where cpf = ?";
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
                PreparedStatement statementClient= conn.prepareStatement(sqlClient);
                statementClient.setString(1, cpf);
                ResultSet resultSetClient = statementClient.executeQuery();
                if(resultSetClient.next() && cpf != "9" ){
                    String telephone = resultSetClient.getString("telephone");
                    String gender = resultSetClient.getString("gender");
                    LocalDate birth_date = LocalDate.parse(resultSetClient.getDate("birth_date").toString());
                    client = new Client( cpf, name, email, password, gender, telephone, birth_date, role);
                }
                
                resultSetClient.close();
                statementClient.close();
            }
            

            
            resultSetUser.close();
            statementUser.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        
        return client;
    }


}