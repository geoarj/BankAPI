package utils;

import database.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static queries.AccountQueries.COUNT_ACCOUNTS;
import static queries.CardQueries.COUNT_CARDS;

public class Generators {

    public static long generateCardNumber() throws ClassNotFoundException, SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_CARDS.toString());
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        return 4276000000000001L + (set.getLong("count"));
    }

    public static long generateAccountNumber() throws ClassNotFoundException, SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ACCOUNTS.toString());
        ResultSet set = preparedStatement.executeQuery();
        set.next();
        return 4081780000000000001L + (set.getLong("count"));
    }

}
