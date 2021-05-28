package database;

import model.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCUtils.getConnection;
import static database.JDBCUtils.printSQLException;
import static queries.CardQueries.*;
import static utils.Generators.generateCardNumber;

public class CardCRUD {

    public void issueNewCardByAccountId(int accountId){
        System.out.println(ISSUE_NEW_CARD_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ISSUE_NEW_CARD_SQL.toString())) {
            preparedStatement.setInt(1, accountId);
            preparedStatement.setLong(2, generateCardNumber());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Card> showCardsByClientId(int clientID) {
    List<Card> list = new ArrayList<>();
    try (Connection connection = JDBCUtils.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_CARDS_BY_CLIENT_ID.toString())) {
        preparedStatement.setInt(1, clientID);
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Card card = new Card();
            card.setCardId(rs.getInt("cardId"));
            card.setAccountId(rs.getInt("clientId"));
            card.setCardNumber(rs.getLong("cardNumber"));
            list.add(card);
        }
    } catch (SQLException e) {
        JDBCUtils.printSQLException(e);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    list.forEach(System.out::println);
    return list;
}

    public void updateRecord(Card card) {
        System.out.println(UPDATE_CARDS_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CARDS_SQL.toString())) {
            preparedStatement.setLong(1, card.getCardNumber());
            preparedStatement.setInt(2, card.getAccountId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(int cardId) {
        System.out.println(DELETE_CARDS_SQL.toString());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CARDS_SQL.toString())) {
            preparedStatement.setInt(1, cardId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
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
        System.out.println(DROP_CARDS.toString());
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(DROP_CARDS.toString());
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
