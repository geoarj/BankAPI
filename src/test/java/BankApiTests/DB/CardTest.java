package BankApiTests.DB;

import database.AccountCRUD;
import database.CardCRUD;
import model.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardTest {

    private final CardCRUD cardCRUD = new CardCRUD();
    private static final List<Card> CARDS = new ArrayList<>();
//BeforeAll or BeforeEach? this method ->
    @Before
    public void doBefore() throws SQLException, ClassNotFoundException {
        CARDS.clear();
        for (int i = 1; i <= 10; i++) {
            CARDS.add(new Card(1, 4276000000000001L));
        }
        cardCRUD.createTable();
    }

    @Test
    public void issueCard() throws SQLException, ClassNotFoundException {
        cardCRUD.issueNewCardByAccountId(1);
        cardCRUD.issueNewCardByAccountId(2);
        List<Card> cardList = cardCRUD.showCardsByClientId(1);
        Assert.assertEquals(2, cardList.size());
    }

//    @Test
//    public void updateCard() throws SQLException {
//        for (Card card : CARDS) {
//            cardCRUD.issueNewCardByAccountId(card.getCardId());
//        }
//        BigDecimal balance = 200000.200;
//        for (Card card : CARDS) {
//            Assert.assertEquals(0.0, acc.getBalance(), 0.0);
//            accountCrud.setBalance(account.getNumber(), v);
//        }
//        for (ru.sberbank.mvc.models.IndividualPartyAccount account : ACCOUNTS) {
//            Assert.assertEquals(v, accountCrud.selectAccByNumber(
//                    account.getNumber()).getBalance(), 0.0);
//        }
//    }

//    @Test
//    public void deleteAccount() throws SQLException {
//        for (ru.sberbank.mvc.models.IndividualPartyAccount account : ACCOUNTS) {
//            accountCrud.insertRecord(account.getId());
//        }
//        for (ru.sberbank.mvc.models.IndividualPartyAccount account : ACCOUNTS) {
//            accountCrud.deleteAccount(account.getNumber());
//        }
//        for (ru.sberbank.mvc.models.IndividualPartyAccount account : ACCOUNTS) {
//            Assert.assertNull(accountCrud.selectAccByNumber(account.getNumber()));
//        }
//    }
}
