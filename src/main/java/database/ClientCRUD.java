package database;

import model.Account;
import model.Card;
import model.Client;
import queries.ClientQueries;

import java.sql.*;

import static database.JDBCUtils.getConnection;
import static database.JDBCUtils.printSQLException;
import static queries.ClientQueries.*;

public class ClientCRUD {

    public void insertRecord(String name, String phoneNumber, String email, String passportNumber) {
        System.out.println(INSERT_CLIENTS_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENTS_SQL.toString())) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, passportNumber);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Client selectRecord(int id) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_CLIENTS.toString())) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int clientId = rs.getInt("clientId");
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String passportNumber = rs.getString("passportNumber");
                System.out.println(clientId + ", " + name + ", " + phoneNumber + ", " + email + ", " + passportNumber);
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRecord(Card card) {
        Account account = new Account();
        Client client = new Client();
        System.out.println(UPDATE_CLIENTS_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENTS_SQL.toString())) {
            preparedStatement.setInt(1, account.getAccountId());
            preparedStatement.setInt(2, client.getClientId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int id) {
        System.out.println(DELETE_CLIENTS_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENTS_SQL.toString())) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        System.out.println(ClientQueries.CREATE_TABLE_SQL.toString());
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(ClientQueries.CREATE_TABLE_SQL.toString());
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
