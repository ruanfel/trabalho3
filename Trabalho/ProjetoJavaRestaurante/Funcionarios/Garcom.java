package Funcionarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Main.Pessoa;

public class Garcom extends Chefe implements AcoesFuncionarios {

    private int id;
    private String cargo;

    public Garcom(int id, String nome, int idade, String cpf, String cargo) {
        super(id, nome, idade, cpf, cargo);
        this.id = id;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Idade: %d | CPF: %s | Cargo: %s",
                id, getNome(), getIdade(), getCpf(), cargo);
    }

    public String toCSV() {
        return String.format("ID: %d | Nome: %s | Idade: %d | CPF: %s | Cargo: %s",
                id, getNome(), getIdade(), getCpf(), cargo);
    }

    public static void cadastrarGarcom(Scanner scanner, List<Garcom> garcons) {
        System.out.print("Digite o id do garçom: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o nome do garçom: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do garçom: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o CPF do garçom: ");
        String cpf = scanner.nextLine();

        String cargo = "Garçom";

        Garcom garcom = new Garcom(id, nome, idade, cpf, cargo);
        garcons.add(garcom);
        System.out.println("Garçom cadastrado com sucesso!");
    }
    
    public static void salvarGarcons(String nomeArquivo, List<Garcom> garcons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Garcom garcom : garcons) {
                writer.write(garcom.toCSV());
                writer.newLine();  
            }
            System.out.println("Garçons salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar garçons: " + e.getMessage());
        }
    }

    public void carregar(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(" \\| ");
                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[0].split(": ")[1]);
                    String nome = dados[1].split(": ")[1];
                    int idade = Integer.parseInt(dados[2].split(": ")[1]);
                    String cpf = dados[3].split(": ")[1];
                    String cargo = dados[4].split(": ")[1];

                    if (cpf.equals(getCpf())) { 
                        setId(id);
                        setNome(nome);
                        setIdade(idade);
                        setCpf(cpf);
                        setCargo(cargo);
                        System.out.println("Garçom carregado com sucesso.");
                        return;
                    }
                }
            }
            System.err.println("Garçom não encontrado no arquivo.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar garçom: " + e.getMessage());
        }
    }

    @Override
    public void realizarTarefa() {
        System.out.println("O garçom está servindo mesas.");
    }

    @Override
    public void atenderCliente() {
        System.out.println("O garçom está atendendo os pedidos dos clientes.");
    }

    @Override
    public void organizarEvento() {
        System.out.println("O garçom está preparando o salão para um evento.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
