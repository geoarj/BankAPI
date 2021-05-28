import database.AccountCRUD;
import database.*;
import database.ClientCRUD;

import java.math.BigDecimal;

import static server.MyHttpServer.startHttpServer;

public class App {
    public static void main(String[] args) {
        AccountCRUD accountCRUD = new AccountCRUD();
        CardCRUD cardCRUD = new CardCRUD();
        ClientCRUD clientCRUD = new ClientCRUD();

//        startHttpServer();
        clientCRUD.createTable();
        accountCRUD.createTable();
        cardCRUD.createTable();

//        Запись нового счета
//        accountCRUD.insertRecord(BigDecimal.valueOf(10000000.10), 1);
//        accountCRUD.insertRecord(BigDecimal.valueOf(11000000.10), 2);
//        accountCRUD.insertRecord(BigDecimal.valueOf(12000000.10), 3);

//        1) Выпуск новой карты по счету
//        cardCRUD.issueNewCardByAccountId(1);
//        cardCRUD.issueNewCardByAccountId(2);
//        cardCRUD.issueNewCardByAccountId(3);
// apache random
//        Добавить клиентов
//        clientCRUD.insertRecord("Афанасьев Павел", "79175059401", "laim@gmail.com", "4516412546");
//        clientCRUD.insertRecord("Варонов Павел", "79175059431", "aaim@gmail.com", "4516412542");
//        clientCRUD.insertRecord("Иванов Кат", "79175059402", "laim1@gmail.com", "4516412541");
//        clientCRUD.insertRecord("Мураков Ап", "79175059422", "l2im@gmail.com", "4516412544");
//        clientCRUD.insertRecord("Бидовой Атон", "79175059404", "l1im@gmail.com", "4516412543");
//        clientCRUD.insertRecord("Капитан Ротс", "79175059405", "l4im@gmail.com", "4516412544");

//        2) Проcмотр списка карт одного клиента
//        cardCRUD.showCardsByClientId(2);

//        3) Внесение средств
//        accountCRUD.depositMoney(3, BigDecimal.valueOf(500));

//        4) Проверка баланса
//        accountCRUD.getBalanceInfo(4081781009991000111L);


    }
}
