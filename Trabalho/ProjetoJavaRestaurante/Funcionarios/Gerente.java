package Funcionarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Gerente extends Chefe implements AcoesFuncionarios {

    public Gerente(int id, String nome, int idade, String cpf, String cargo) {
        super(id, nome, idade, cpf, cargo);
    }

    public String toCSV() {
        return String.format("Id: %d; Nome: %s; Idade: %d; CPF: %s; Cargo: %s",
                getId(), getNome(), getIdade(), getCpf(), getCargo());
    }
    

    public static void cadastrarGerente(Scanner scanner, List<Gerente> gerentes) {
        System.out.print("Digite o id do gerente: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o nome do gerente: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do gerente: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o CPF do gerente: ");
        String cpf = scanner.nextLine();

        String cargo = "Gerente";

        Gerente gerente = new Gerente(id, nome, idade, cpf, cargo);
        gerentes.add(gerente);
    }

    @Override
    public void realizarTarefa() {
        System.out.println("O gerente está coordenando as operações do restaurante.");
    }

    @Override
    public void atenderCliente() {
        System.out.println("O gerente está resolvendo um problema de um cliente.");
    }

    @Override
    public void organizarEvento() {
        System.out.println("O gerente está revisando o planejamento de um evento.");
    }

    public static void salvarGerentes(String nomeArquivo, List<Gerente> gerentes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Gerente gerente : gerentes) {
                writer.write(gerente.toCSV()); 
                writer.newLine();  
            }
            System.out.println("Gerentes salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar gerentes: " + e.getMessage());
        }
    }
    

    public void carregar(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    setNome(dados[1]);
                    setCpf(dados[2]);
                    setIdade(Integer.parseInt(dados[3]));
                    System.out.println("Gerente carregado com sucesso.");
                    return;
                }
            }
            System.err.println("Gerente não encontrado no arquivo.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar Gerente: " + e.getMessage());
        }
    }
}