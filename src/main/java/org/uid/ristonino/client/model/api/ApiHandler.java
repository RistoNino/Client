package org.uid.ristonino.client.model.api;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;


public class ApiHandler {
    public void sendOrder(JSONObject order) {
        try {
            // Definisci l'URL del servizio che vuoi chiamare
            URL url = new URL("http://example.com/api/resource");

            // Apre una connessione HTTP
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Crea un oggetto JSON con i dati da inviare
            JSONObject jsonInput = new JSONObject();
            jsonInput.put("param1", "value1");
            jsonInput.put("param2", "value2");

            // Scrivi i dati JSON nel corpo della richiesta
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                byte[] postData = jsonInput.toString().getBytes();
                wr.write(postData);
            }

            // Leggi la risposta
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();

            // Stampa la risposta
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
