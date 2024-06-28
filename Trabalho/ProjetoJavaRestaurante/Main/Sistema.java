package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Cliente.Cliente;
import Funcionarios.Auxiliar;
import Funcionarios.Chefe;
import Funcionarios.Garcom;
import Funcionarios.Gerente;
import Funcionarios.Recepcionista;
import Cardapio.*;

public class Sistema {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static Scanner scanner;

    public static void main(String[] args) {
        iniciarSistema();
    }

    public static void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);
        List<Chefe> chefs = new ArrayList<>();
        List<Garcom> garcons = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<Auxiliar> auxiliares = new ArrayList<>();
        List<Recepcionista> recepcionistas = new ArrayList<>();
        List<Gerente> gerentes = new ArrayList<>();
        Cardapio cardapio = new Cardapio();

        cardapio.adicionarPrato("Hambúrguer", 20.0);
        cardapio.adicionarPrato("Sopa de Legumes", 18.0);
        cardapio.adicionarPrato("Macarrão à Bolonhesa", 22.0);
        cardapio.adicionarPrato("Frango Grelhado", 27.0);
        cardapio.adicionarPrato("Salmão ao Molho de Maracujá", 35.0);

        boolean continuar = true;

        while (continuar) {
            System.out.println(ANSI_YELLOW + "\n****************************************");
            System.out.println("          SISTEMA DE CADASTRO           ");
            System.out.println("****************************************" + ANSI_RESET);
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Cadastro de Funcionários");
            System.out.println("2. Cadastro de Clientes e Pedidos");
            System.out.println("3. Listagem de Cadastrados");
            System.out.println("4. Sair");
            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    menuCadastroFuncionarios(scanner, chefs, garcons, auxiliares, recepcionistas, gerentes);
                    break;
                case 2:
                    menuCadastroClientes(scanner, clientes);
                    break;
                case 3:
                    menuListagemCadastrados(scanner, chefs, garcons, auxiliares, recepcionistas, gerentes, clientes);
                    break;
                case 4:
                    continuar = false;
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println(ANSI_YELLOW + "****************************************");
        }
        scanner.close();
    }

    public static void menuCadastroFuncionarios(Scanner scanner, List<Chefe> chefs, List<Garcom> garcons,
            List<Auxiliar> auxiliares, List<Recepcionista> recepcionistas, List<Gerente> gerentes) {
        boolean continuar = true;

        while (continuar) {
            System.out.println(ANSI_YELLOW + "\n****************************************");
            System.out.println("       CADASTRO DE FUNCIONÁRIOS         ");
            System.out.println("****************************************" + ANSI_RESET);
            System.out.println("=== Escolha o que deseja cadastrar ===");
            System.out.println("1. Chefe");
            System.out.println("2. Garçom");
            System.out.println("3. Auxiliar");
            System.out.println("4. Recepcionista");
            System.out.println("5. Gerente");
            System.out.println("6. Voltar");
            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Chefe.cadastrarChefe(scanner, chefs);
                    Chefe.salvarChefs("chefs.txt", chefs);
                    break;
                case 2:
                    Garcom.cadastrarGarcom(scanner, garcons);
                    Garcom.salvarGarcons("garcons.txt", garcons);
                    break;
                case 3:
                    Auxiliar.cadastrarAuxiliar(scanner, auxiliares);
                    Auxiliar.salvarAuxiliares("auxiliares.txt", auxiliares);
                    break;
                case 4:
                    Recepcionista.cadastrarRecepcionista(scanner, recepcionistas);
                    Recepcionista.salvarRecepcionistas("recepcionistas.txt", recepcionistas);
                    break;
                case 5:
                    Gerente.cadastrarGerente(scanner, gerentes);
                    Gerente.salvarGerentes("gerentes.txt", gerentes);
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuCadastroClientes(Scanner scanner, List<Cliente> clientes) {
        boolean continuar = true;

        while (continuar) {
            System.out.println(ANSI_YELLOW + "\n****************************************");
            System.out.println("     CADASTRO DE CLIENTES E PEDIDOS     ");
            System.out.println("****************************************"+ ANSI_RESET);
            System.out.println("=== Escolha o que deseja cadastrar ===" );
            System.out.println("1. Cliente");
            System.out.println("2. Voltar");
            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    Cliente.cadastrarCliente(scanner, clientes);
                    Cliente.salvarClientes("clientes.txt", clientes);
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void menuListagemCadastrados(Scanner scanner, List<Chefe> chefs, List<Garcom> garcons, List<Auxiliar> auxiliares,
    List<Recepcionista> recepcionistas, List<Gerente> gerentes, List<Cliente> clientes) {
        boolean continuar = true;

        while (continuar) {
            System.out.println(ANSI_YELLOW + "\n****************************************");
            System.out.println("       LISTAGEM DE CADASTRADOS          ");
            System.out.println("****************************************" + ANSI_RESET);
            System.out.println("=== Escolha o que deseja listar ===");
            System.out.println("1. Chefes");
            System.out.println("2. Garçons");
            System.out.println("3. Recepcionistas");
            System.out.println("4. Auxiliares");
            System.out.println("5. Gerentes");
            System.out.println("6. Clientes");
            System.out.println("7. Voltar");
            System.out.print("Digite sua escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    listarChefes(chefs);
                    break;
                case 2:
                    listarGarcons(garcons);
                    break;
                case 3:
                    listarRecepcionistas(recepcionistas);
                    break;
                case 4:
                    listarAuxiliares(auxiliares);
                    break;
                case 5:
                    listarGerentes(gerentes);
                    break;
                case 6:
                    listarClientes(clientes);
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void listarChefes(List<Chefe> chefs) {
        System.out.println("\n=== Chefes Cadastrados ===");
        if (chefs.isEmpty()) {
            System.out.println("Nenhum chefe cadastrado.");
        } else {
            for (Chefe chefe : chefs) {
                System.out.println(chefe);
            }
        }
    }

    public static void listarGarcons(List<Garcom> garcons) {
    System.out.println("=== Garçons Cadastrados ===");
    for (Garcom garcom : garcons) {
    System.out.println(garcom); 
}

    }

    public static void listarRecepcionistas(List<Recepcionista> recepcionistas) {
        System.out.println("\n=== Recepcionistas Cadastrados ===");
        if (recepcionistas.isEmpty()) {
            System.out.println("Nenhum recepcionista cadastrado.");
        } else {
            for (Recepcionista recepcionista : recepcionistas) {
                System.out.println(recepcionista);
            }
        }
    }

    public static void listarAuxiliares(List<Auxiliar> auxiliares) {
        System.out.println("\n=== Auxiliares Cadastrados ===");
        if (auxiliares.isEmpty()) {
            System.out.println("Nenhum auxiliar cadastrado.");
        } else {
            for (Auxiliar auxiliar : auxiliares) {
                System.out.println(auxiliar);
            }
        }
    }

    public static void listarGerentes(List<Gerente> gerentes) {
        System.out.println("\n=== Gerentes Cadastrados ===");
        if (gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado.");
        } else {
            for (Gerente gerente : gerentes) {
                System.out.println(gerente);
            }
        }
    }

    public static void listarClientes(List<Cliente> clientes) {
        System.out.println("\n=== Clientes Cadastrados ===");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
}
