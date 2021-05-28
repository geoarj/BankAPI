package database;

import java.math.BigDecimal;
import java.sql.*;

import static database.JDBCUtils.getConnection;
import static database.JDBCUtils.printSQLException;
import static queries.AccountQueries.*;
import static utils.Generators.generateAccountNumber;

public class AccountCRUD {

    public void insertRecord(BigDecimal balance, int clientId) throws SQLException {
        System.out.println(INSERT_ACCOUNTS_SQL.toString());
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNTS_SQL.toString())) {
            preparedStatement.setLong(1, generateAccountNumber());
            preparedStatement.setBigDecimal(2, balance);
            preparedStatement.setInt(3, clientId);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getBalanceInfo(int accountId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BALANCE.toString())) {
            preparedStatement.setInt(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getBigDecimal("balance");
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(-1.0);
    }

    public void depositMoney(int accountId, BigDecimal sum) throws Exception {
        System.out.println(UPDATE_ACCOUNTS_SQL.toString());
        BigDecimal balance = getBalanceInfo(accountId);
        if (balance.equals(-1.0)) {
            throw new Exception("Что-то пошло не так!");
        }
        BigDecimal newBalance = balance.add(sum);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNTS_SQL.toString())) {
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
            System.out.println("AccountId = " + accountId + ", updated balance = "+ newBalance + ".");
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void withDrawMoney(int accountId, BigDecimal sum) throws Exception {
        System.out.println(UPDATE_ACCOUNTS_SQL.toString());
        BigDecimal balance = getBalanceInfo(accountId);
        if (balance.equals(-1.0)) {
            throw new Exception("Что-то пошло не так!");
        }
        BigDecimal newBalance = balance.subtract(sum);
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNTS_SQL.toString())) {
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
            System.out.println("AccountId = " + accountId + ", updated balance = "+ newBalance + ".");
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int accountId) {
        System.out.println(DELETE_ACCOUNTS_SQL.toString());
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNTS_SQL.toString())) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        System.out.println(CREATE_TABLE_SQL.toString());
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL.toString());
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        System.out.println(DROP_ACCOUNTS.toString());
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_ACCOUNTS.toString());
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
