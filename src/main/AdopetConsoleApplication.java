package br.com.alura;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdopetConsoleApplication {

    private static final Logger LOGGER = Logger.getLogger(AdopetConsoleApplication.class.getName());

    public static void main(String[] args) {
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        try (Scanner scanner = new Scanner(System.in)) {
            CommandExecutor executor = new CommandExecutor();
            int opcaoEscolhida;

            do {
                exibirMenu();
                opcaoEscolhida = lerOpcaoUsuario(scanner);
                executarOpcao(opcaoEscolhida, executor);
            } while (opcaoEscolhida != 5);

            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro inesperado no sistema", e);
        }
    }

    private static void exibirMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }

    private static int lerOpcaoUsuario(Scanner scanner) {
        int opcao = 0;
        while (opcao < 1 || opcao > 5) {
            System.out.print("Escolha uma opção (1-5): ");
            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
                if (opcao < 1 || opcao > 5) {
                    System.out.println("Opção inválida! Digite um número entre 1 e 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número entre 1 e 5.");
            }
        }
        return opcao;
    }

    private static void executarOpcao(int opcao, CommandExecutor executor) {
        switch (opcao) {
            case 1 -> executor.executeCommand(new ListarAbrigoCommand());
            case 2 -> executor.executeCommand(new CadastrarAbrigoCommand());
            case 3 -> executor.executeCommand(new ListarPetsDoAbrigoCommand());
            case 4 -> executor.executeCommand(new ImportarPetsDoAbrigoCommand());
            case 5 -> System.out.println("Encerrando o sistema...");
        }
    }
}
