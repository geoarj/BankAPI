package server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class MyHttpServer  {

        public static void startHttpServer() throws IOException {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
            Logger logger = Logger.getLogger("myLogger");

            CardHandler cardHandler = new CardHandler();
            AccountHandler accountHandler = new AccountHandler();

            server.createContext("/issueNewCardByAccount", cardHandler);
            server.createContext("/getAllCardsOfClient", cardHandler);

            server.createContext("/getBalanceInfo", accountHandler);
            server.createContext("/depositMoney", accountHandler);

            server.start();
            logger.info("Server started on port 8080");
        }
}
