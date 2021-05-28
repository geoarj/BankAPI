package queries;

public enum AccountQueries {

        CREATE_TABLE_SQL(" CREATE TABLE ACCOUNTS (\n" +
            "                          accountId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "                          accountNumber BIGINT NOT NULL,\n" +
            "                          balance DOUBLE NOT NULL,\n" +
            "                          clientId INT NOT NULL,\n" +
            "                          FOREIGN KEY (clientId) REFERENCES CLIENTS(clientId) ON DELETE CASCADE\n" +
            ");"),

        INSERT_ACCOUNTS_SQL("INSERT INTO accounts" +
                            " (accountNumber, balance, clientId) VALUES " +
                            " (?, ?, ?);"),

        SELECT_QUERY_BALANCE("SELECT * FROM accounts WHERE accountId = ?;"),

        UPDATE_ACCOUNTS_SQL("UPDATE accounts SET balance = ? WHERE accountId = ?;"),

        COUNT_ACCOUNTS("SELECT COUNT(*) AS count FROM accounts;"),

        DELETE_ACCOUNTS_SQL("DELETE FROM accounts WHERE accountId = ?"),

        DROP_ACCOUNTS("DROP TABLE ACCOUNTS");

        private String query;

        AccountQueries(String query) {
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
