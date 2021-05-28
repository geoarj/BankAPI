package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import model.Account;
import model.BalanceRequest;
import services.AccountService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class AccountHandler implements HttpHandler {

    AccountService accountService = new AccountService();
    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void handle(HttpExchange httpExchange) {
        if ("get".equalsIgnoreCase(httpExchange.getRequestMethod())) {
            handleGetRequestGetBalanceInfo(httpExchange);
        } else if ("post".equalsIgnoreCase(httpExchange.getRequestMethod())) {
                handlePostRequestDeposit(httpExchange);
        } else {
            throw new IOException("Format is not supported!");
        }
    }

    private void handleGetRequestGetBalanceInfo(HttpExchange httpExchange) throws IOException {
        int accountId = Integer.parseInt(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1]);
        BigDecimal balance = accountService.getBalanceInfo(accountId);
        BalanceRequest balanceRequest = new BalanceRequest(accountId, balance);
        String result = objectMapper.writeValueAsString(balanceRequest);

        httpExchange.sendResponseHeaders(200, result.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(result.getBytes());
        outputStream.close();
    }

    private void handlePostRequestDeposit(HttpExchange httpExchange) throws Exception {
        InputStreamReader inputStream = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        StringBuilder stringBuilder = new StringBuilder(512);
        int i;
        while ((i = bufferedReader.read()) != -1) {
            stringBuilder.append((char)i);
        }
        bufferedReader.close();
        inputStream.close();

        System.out.println(stringBuilder.toString());

        Account account = objectMapper.readValue(stringBuilder.toString(), Account.class);

        System.out.println(account.toString());
        int accountId = account.getAccountId();
        BigDecimal sum = account.getBalance();
        accountService.depositMoney(accountId, sum);
    }



}
