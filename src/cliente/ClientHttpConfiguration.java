package br.com.alura.cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ClientHttpConfiguration {

    private final HttpClient client;
    private final Gson gson;

    public ClientHttpConfiguration() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public HttpResponse<String> dispararRequisicaoPost(String uri, Object object) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(object)))
                .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar requisição POST para: " + uri, e);
        }
    }

    public HttpResponse<String> dispararRequisicaoGet(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao enviar requisição GET para: " + uri, e);
        }
    }
}
