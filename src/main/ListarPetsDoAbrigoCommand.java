package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

public class ListarPetsDoAbrigoCommand implements Command {
    @Override
    public void execute() {
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetService petService = new PetService(client);

            petService.listarPetsDoAbrigo();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
