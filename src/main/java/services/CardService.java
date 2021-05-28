package services;

import database.*;
import model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardService {

    CardCRUD cardCRUD = new CardCRUD();

    public void issueNewCardByAccountId(int accountId) {
        cardCRUD.issueNewCardByAccountId(accountId);
    }

    public List<Card> showCardsByClientId(int clientId) {
        List<Card> list = new ArrayList<>();
        cardCRUD.showCardsByClientId(clientId);
        return list;
    }

}
