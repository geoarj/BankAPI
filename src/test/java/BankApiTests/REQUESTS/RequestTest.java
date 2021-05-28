package BankApiTests.REQUESTS;

import database.CardCRUD;
import model.Card;
import org.junit.Test;
import server.MyHttpServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestTest {


    @Test
    public void shouldAddCardToTable() throws Exception {

//        MyHttpServer myHttpServer = new MyHttpServer();
////        myHttpServer.startServer();

        CardCRUD cardCRUD = new CardCRUD();
        cardCRUD.createTable();

        final URL url = new URL("http://localhost:8080/issueNewCardByAccount");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setDoOutput(true);
        String json = "{\n" +
                "    \"accountNumber\": 40817810400000000001\n" +
                "}";

        System.out.println(json);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("UTF-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            br.close();
            System.out.println(response.toString());
            CardCRUD cardCRUD1 = new CardCRUD();
//            System.out.println(" \n\n " + cardCRUD1.showCardsByClientId());
        }
    }
}
