package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Cardapio.*;

public class Cliente extends Main.Pessoa {

    private String telefone;
    private String email;
    private List<Prato> pedido;
    private Prato escolhaPrato;

    public Cliente(String nome, int idade, String cpf, String telefone, String email) {
        super(nome, idade, cpf);
        this.telefone = telefone;
        this.email = email;
        this.pedido = new ArrayList<>();
    }

    @Override
    public String toString() {
        String pedidoString = (escolhaPrato != null) ? escolhaPrato.getNome() + " - R$" + escolhaPrato.getPreco()
                : "Nenhum prato escolhido";
        return "Cliente: " + getNome() + "\nIdade: " + getIdade() + "\nCPF: " + getCpf() + "\nTelefone: " + telefone
                + "\nEmail: " + email + "\nPedido: " + pedidoString;
    }

    public void adicionarAoPedido(Prato prato) {
        this.escolhaPrato = prato;
        pedido.add(prato);
    }

    public void listarPedido() {
        System.out.println("Pedido do Cliente " + getNome() + ":");
        for (Prato prato : pedido) {
            System.out.printf("%s - R$%.2f%n", prato.getNome(), prato.getPreco());
        }
    }

    public static void cadastrarCliente(Scanner scanner, List<Cliente> clientes) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do cliente: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o telefone do cliente: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nome, idade, cpf, telefone, email);
        clientes.add(cliente);

        boolean escolherPrato = true;
        do {
            System.out.println("\n=== Cardápio ===");
            for (int i = 0; i < Cardapio.pratos.size(); i++) {
                Prato prato = Cardapio.pratos.get(i);
                System.out.printf("%d. %s - R$%.2f%n", i + 1, prato.getNome(), prato.getPreco());
            }
            System.out.println("0. Concluir pedido");

            System.out.print("Escolha um prato (ou 0 para concluir): ");
            int escolhaPrato = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaPrato) {
                case 0:
                    escolherPrato = false;
                    break;
                default:
                    if (escolhaPrato >= 1 && escolhaPrato <= Cardapio.pratos.size()) {
                        Prato pratoEscolhido = Cardapio.pratos.get(escolhaPrato - 1);
                        cliente.adicionarAoPedido(pratoEscolhido);
                        System.out.println(pratoEscolhido.getNome() + " adicionado ao pedido.");
                        break;
                    } else {
                        System.out.println("Opção inválida. Escolha novamente.");
                    }
                    break;
            }
        } while (escolherPrato);
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void salvar(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(String.format("Nome: %s; Idade: %d; CPF: %s; Telefone: %s; Email: %s\n", getNome(), getIdade(),
                    getCpf(), telefone, email));
            System.out.println("Cliente salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar cliente: " + e.getMessage());
        }
    }

    public static void salvarClientes(String nomeArquivo, List<Cliente> clientes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Cliente cliente : clientes) {
                writer.write(String.format("Nome: %s; Idade: %d; CPF: %s; Telefone: %s; Email: %s\n", cliente.getNome(),
                        cliente.getIdade(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail()));
            }
            System.out.println("Clientes salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public void carregar(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5 && dados[0].contains(getNome()) && dados[2].contains(getCpf())) {
                    setIdade(Integer.parseInt(dados[1]));
                    setTelefone(dados[3]);
                    setEmail(dados[4]);
                    System.out.println("Cliente carregado com sucesso.");
                    return;
                }
            }
            System.err.println("Cliente não encontrado no arquivo.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar cliente: " + e.getMessage());
        }
    }

    public String toCSV() {
        String pedidoString = (escolhaPrato != null) ? escolhaPrato.getNome() + " - R$" + escolhaPrato.getPreco()
                : "Nenhum prato escolhido";
        return String.format("%s;%d;%s;%s;%s", getNome(), getIdade(), getCpf(), telefone, email);
    }
}
