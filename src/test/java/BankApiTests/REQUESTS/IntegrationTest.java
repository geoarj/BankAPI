package BankApiTests.REQUESTS;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IntegrationTest {

    final URL url = new URL("http://localhost:8080/getAllCardsOfClient?clientId=1");
    final HttpURLConnection con = (HttpURLConnection) url.openConnection();

    public IntegrationTest() throws IOException {
    }

    @Test
    public void integrationTest() throws IOException {
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5);

        con.setReadTimeout(5);
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return;
        } catch (final Exception ex) {
            ex.printStackTrace();

        }
        return;
    }
}
