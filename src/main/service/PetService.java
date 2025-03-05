package br.com.alura.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.alura.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.alura.client.ClientHttpConfiguration;

public class PetService {

    private final ClientHttpConfiguration client;
    private final ObjectMapper objectMapper;
    private final Scanner scanner;
    private static final Logger LOGGER = Logger.getLogger(PetService.class.getName());

    public PetService(ClientHttpConfiguration client) {
        this.client = client;
        this.objectMapper = new ObjectMapper();
        this.scanner = new Scanner(System.in);
    }

    public void listarPetsDoAbrigo() {
        try {
            String idOuNome = lerIdOuNomeDoAbrigo();

            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
            HttpResponse<String> response = this.client.dispararRequisicaoGet(uri);

            if (response.statusCode() == 404) {
                System.out.println("ID ou nome não cadastrado!");
                return;
            }

            List<Pet> petList = Arrays.asList(objectMapper.readValue(response.body(), Pet[].class));
            
            if (petList.isEmpty()) {
                System.out.println("Nenhum pet encontrado neste abrigo.");
            } else {
                mostrarPets(petList);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar JSON da resposta", e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao listar pets", e);
        }
    }

    private void mostrarPets(List<Pet> petList) {
        System.out.println("Pets cadastrados:");
        petList.forEach(pet -> 
            System.out.println(pet.getId() + " - " + pet.getTipo() + " - " + pet.getNome() 
                               + " - " + pet.getRaca() + " - " + pet.getIdade() + " ano(s)")
        );
    }

    public void importarPetsDoAbrigo() {
        try {
            String idOuNome = lerIdOuNomeDoAbrigo();
            String nomeArquivo = lerNomeArquivo();

            List<Pet> pets = lerPetsDoArquivo(nomeArquivo);
            if (pets.isEmpty()) {
                System.out.println("Nenhum pet encontrado no arquivo.");
                return;
            }

            enviarPetsParaServidor(idOuNome, pets);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao importar pets do abrigo", e);
        }
    }

    private List<Pet> lerPetsDoArquivo(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            return reader.lines().map(t -> converterLinhaParaPet(t)).toList();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar o arquivo: " + nomeArquivo, e);
            return List.of();
        }
    }

    private Pet converterLinhaParaPet(String line) {
        String[] campos = line.split(",");
        if (campos.length < 6) {
            throw new IllegalArgumentException("Linha do arquivo CSV inválida: " + line);
        }

        return new Pet(
            campos[0],                   // tipo
            campos[1],                   // nome
            campos[2],                   // raça
            Integer.parseInt(campos[3]),  // idade
            campos[4],                   // cor
            Float.parseFloat(campos[5])   // peso
        );
    }

    private void enviarPetsParaServidor(String idOuNome, List<Pet> pets) {
        for (Pet pet : pets) {
            try {
                String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets";
                HttpResponse<String> response = this.client.dispararRequisicaoPost(uri, pet);

                if (response.statusCode() == 200) {
                    System.out.println("Pet cadastrado com sucesso: " + pet.getNome());
                } else {
                    System.out.println("Erro ao cadastrar o pet: " + pet.getNome() + " - " + response.body());
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao enviar pet para o servidor: " + pet.getNome(), e);
            }
        }
    }

    private String lerIdOuNomeDoAbrigo() {
        System.out.println("Digite o ID ou nome do abrigo:");
        return scanner.nextLine();
    }

    private String lerNomeArquivo() {
        System.out.println("Digite o nome do arquivo CSV:");
        return scanner.nextLine();
    }
}
