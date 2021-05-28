package BankApiTests.DB;

import database.AccountCRUD;
import model.Account;
import model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountTest {

    private final AccountCRUD accountCRUD = new AccountCRUD();
    private static final List<Account> ACCOUNTS = new ArrayList<>();

//    @Before
//    public void doBefore() throws SQLException, ClassNotFoundException {
//        ACCOUNTS.clear();
//        for (int i = 1; i <= 10; i++) {
//            ACCOUNTS.add(accountCRUD.insertRecord(BigDecimal.valueOf(23233.1323), 2));
//
//        }
//        try {
//            accountCRUD.createTable();
//        } catch (SQLException throwables) {
//            accountCRUD.dropTable();
//            accountCRUD.createTable();
//        }
//    }

    @Test
    public void updateAccount() throws SQLException {
        for (Account account : ACCOUNTS) {
            accountCRUD.insertRecord(account.getBalance(), account.getAccountId());
        }
        BigDecimal balance = BigDecimal.valueOf(200000.200);
        for (Account account : ACCOUNTS) {
            Assert.assertEquals(String.valueOf(BigDecimal.valueOf(0.0)), account.getBalance(), BigDecimal.valueOf(0.0));
            account.setBalance(account.getBalance());
        }
        for (Account account : ACCOUNTS) {
//            Assert.assertEquals(balance, accountCRUD.getBalanceInfo();
//                    account.getAccountNumber(), accountCRUD.getBalanceInfo(account.getAccountId()), 0.0));
        }
    }
}

