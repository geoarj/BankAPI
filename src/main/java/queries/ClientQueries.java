package queries;

public enum ClientQueries {

    CREATE_TABLE_SQL("CREATE TABLE CLIENTS (\n" +
            "                         clientId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "                         name VARCHAR NOT NULL,\n" +
            "                         phoneNumber VARCHAR NOT NULL UNIQUE,\n" +
            "                         email VARCHAR UNIQUE,\n" +
            "                         passportNumber VARCHAR NOT NULL UNIQUE\n" +
            ");"),

    INSERT_CLIENTS_SQL("INSERT INTO CLIENTS" +
            " (name, phoneNumber, email, passportNumber) VALUES " +
            " (?, ?, ?, ?);"),

    SELECT_QUERY_CLIENTS("SELECT * FROM CLIENTS WHERE clientId = ?;"),

    UPDATE_CLIENTS_SQL("UPDATE CLIENTS SET accountId = ? WHERE clientId = ?;"),

    DELETE_CLIENTS_SQL("DELETE FROM CLIENTS WHERE clientId = ?");

    private String query;

    ClientQueries(String query) {
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
