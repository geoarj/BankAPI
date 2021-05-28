package server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import model.Card;
import services.CardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CardHandler implements HttpHandler {

    CardService cardService = new CardService();
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void handle(HttpExchange httpExchange) {
        if ("get".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            handleGetRequestByClientId(httpExchange);
        } else if ("post".equalsIgnoreCase(httpExchange.getRequestMethod())) {
                handlePostRequest(httpExchange);
        } else {
            throw new IOException("Format is not supported!");
        }
    }

    private void handleGetRequestByClientId(HttpExchange httpExchange) throws IOException {
        int clientId = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1]);
        List<Card> list = cardService.showCardsByClientId(clientId);

        ArrayNode array = objectMapper.valueToTree(list);
        JsonNode result = objectMapper.createObjectNode().set("cards", array);

        String cardResult = result.toString();

        httpExchange.sendResponseHeaders(200, cardResult.length());
        OutputStream outputStream = httpExchange.getResponseBody();

        outputStream.write(cardResult.getBytes());
        outputStream.close();
    }

    private void handlePostRequest(HttpExchange httpExchange) throws Exception {

        InputStreamReader inputStream = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        StringBuilder stringBuilder = new StringBuilder(512);
        int i = 0;
        while ((i = bufferedReader.read()) != -1) {
            stringBuilder.append((char)i);
        }
        bufferedReader.close();
        inputStream.close();
        System.out.println(stringBuilder.toString());

        Card card = objectMapper.readValue(stringBuilder.toString(), Card.class);

        System.out.println(card.toString());
        int accountId = card.getAccountId();
        cardService.issueNewCardByAccountId(accountId);
    }
}
