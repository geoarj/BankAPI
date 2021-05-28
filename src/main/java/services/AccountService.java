package services;

import database.AccountCRUD;
import model.Account;

import java.math.BigDecimal;

public class AccountService {

    private AccountCRUD accountCRUD = new AccountCRUD();

    public void depositMoney(int id, BigDecimal sum) throws Exception {
        accountCRUD.depositMoney(id, sum);
    }

    public BigDecimal getBalanceInfo(int id) {
        accountCRUD.getBalanceInfo(id);
        return BigDecimal.valueOf(-1.0);
    }

////   transfer money
//    public Account findAccountById(int id) {
//        return null;
//    }

}
