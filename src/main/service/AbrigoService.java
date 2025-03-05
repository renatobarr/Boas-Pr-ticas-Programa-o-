package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbrigoService {
    
    private final ClientHttpConfiguration client;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = Logger.getLogger(AbrigoService.class.getName());
    private final Scanner scanner;

    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
        this.scanner = new Scanner(System.in); // Criar um scanner único
    }

    public void cadastrarAbrigo() {
        try {
            Abrigo abrigo = lerDadosAbrigo(); // Obtém os dados do usuário

            String uri = "http://localhost:8080/abrigos";
            HttpResponse<String> response = this.client.dispararRequisicaoPost(uri, abrigo);

            processarRespostaCadastro(response);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar a resposta JSON", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar abrigo", e);
        }
    }

    private Abrigo lerDadosAbrigo() {
        System.out.println("Digite o nome do abrigo:");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = scanner.nextLine();

        return new Abrigo(nome, telefone, email);
    }

    private void processarRespostaCadastro(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else {
            System.out.println("Erro ao cadastrar o abrigo: " + responseBody);
        }
    }

    public List<Abrigo> listarAbrigos() {
        try {
            String uri = "http://localhost:8080/abrigos";
            HttpResponse<String> response = this.client.dispararRequisicaoGet(uri);
            String responseBody = response.body();

            Abrigo[] abrigosArray = objectMapper.readValue(responseBody, Abrigo[].class);
            return Arrays.asList(abrigosArray);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar JSON da resposta", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar abrigos", e);
        }

        return List.of(); // Retorna uma lista vazia em caso de erro
    }

    public void mostrarAbrigos() {
        List<Abrigo> abrigoList = listarAbrigos();

        if (abrigoList.isEmpty()) {
            System.out.println("Não há abrigos cadastrados.");
        } else {
            System.out.println("Abrigos cadastrados:");
            abrigoList.forEach(abrigo -> System.out.println(abrigo.getId() + " - " + abrigo.getNome()));
        }
    }
}
