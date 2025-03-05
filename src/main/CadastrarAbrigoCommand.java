package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastrarAbrigoCommand implements Command {
    
    private static final Logger LOGGER = Logger.getLogger(CadastrarAbrigoCommand.class.getName());
    private final AbrigoService abrigoService;

    public CadastrarAbrigoCommand(AbrigoService abrigoService) {
        this.abrigoService = abrigoService;
    }

    @Override
    public void execute() {
        try {
            abrigoService.cadastrarAbrigo();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar abrigo", e);
        }
    }
}
