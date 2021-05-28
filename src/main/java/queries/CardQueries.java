package queries;

public enum CardQueries {

    CREATE_TABLE_SQL(" CREATE TABLE CARDS (\n" +
            "                           cardId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "                           cardNumber BIGINT NOT NULL,\n" +
            "                           accountId INT NOT NULL,\n" +
            "                           FOREIGN KEY (accountId) REFERENCES ACCOUNTS(accountId) ON DELETE CASCADE\n" +
            ");"),

    ISSUE_NEW_CARD_SQL("INSERT INTO cards (accountId, cardNumber) VALUES (?, ?);"),

    SHOW_ALL_CARDS_BY_ACCOUNT_ID("SELECT * FROM cards WHERE accountId = ?;"),

    SELECT_QUERY_CARDS_BY_CLIENT_ID("SELECT * FROM CARDS " +
            "JOIN ACCOUNTS " +
            "ON ACCOUNTS.accountId = CARDS.accountId " +
            "WHERE ACCOUNTS.clientId = ?"),

    UPDATE_CARDS_SQL("UPDATE cards SET cardNumber = ? WHERE accountId = ?;"),

    COUNT_CARDS("SELECT COUNT(*) AS count FROM CARDS;"),

    DELETE_CARDS_SQL("DELETE FROM cards WHERE cardId = ?"),

    DROP_CARDS("DROP TABLE CARDS");

    private String query;

    CardQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return query;
    }
}
