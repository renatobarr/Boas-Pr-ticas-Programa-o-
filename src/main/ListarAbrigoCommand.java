package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

public class ListarAbrigoCommand implements Command {

    @Override
    public void execute() {
        ClientHttpConfiguration client = new ClientHttpConfiguration();
        AbrigoService abrigoService = new AbrigoService(client);

        abrigoService.listarAbrigo();
    }
}
