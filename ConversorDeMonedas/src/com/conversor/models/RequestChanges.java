package ConversorDeMonedas.src.com.conversor.models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RequestChanges {

    public CurrencyChange request(String convertValue) {
          
        String url = "https://v6.exchangerate-api.com/v6/49ac8cba80f427abf135ac97/latest/" + convertValue;
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();  
        try {

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, BodyHandlers.ofString());

            return gson.fromJson(response.body(), CurrencyChange.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
            
}
