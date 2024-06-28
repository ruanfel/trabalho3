package Funcionarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Recepcionista extends Chefe implements AcoesFuncionarios {

    public Recepcionista(int id, String nome, int idade, String cpf, String cargo) {
        super(id, nome, idade, cpf, cargo);
    }

    public String toCSV() {
        return String.format("Id: %d; Nome: %s; Idade: %d; CPF: %s; Cargo: %s",
                getId(), getNome(), getIdade(), getCpf(), getCargo());
    }
    

    public static void cadastrarRecepcionista(Scanner scanner, List<Recepcionista> recepcionistas) {
        System.out.print("Digite o id da recepcionista: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome da recepcionista: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade da recepcionista: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o CPF da recepcionista: ");
        String cpf = scanner.nextLine();

        String cargo = "Recepcionista";

        Recepcionista recepcionista = new Recepcionista(id, nome, idade, cpf, cargo);
        recepcionistas.add(recepcionista);
    }

    public static void salvarRecepcionistas(String nomeArquivo, List<Recepcionista> recepcionistas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Recepcionista recepcionista : recepcionistas) {
                writer.write(recepcionista.toCSV());  
                writer.newLine(); 
            }
            System.out.println("Recepcionistas salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar recepcionistas: " + e.getMessage());
        }
    }
    


    @Override
    public void realizarTarefa() {
        System.out.println("A recepcionista está recebendo os clientes.");
    }

    @Override
    public void atenderCliente() {
        System.out.println("A recepcionista está informando os clientes sobre as mesas disponíveis.");
    }

    @Override
    public void organizarEvento() {
        System.out.println("A recepcionista está preparando o registro para um evento.");
    }

    public void salvar(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            writer.write(String.format("%d;%s;%d;%s;%s\n", getId(), getNome(), getIdade(), getCpf(), getCargo()));
            System.out.println("Recepcionista salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar recepcionista: " + e.getMessage());
        }
    }

    public void carregar(String nomeArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    int idade = Integer.parseInt(dados[2]);
                    String cpf = dados[3];
                    String cargo = dados[4];

                    if (cpf.equals(getCpf())) { 
                        setId(id);
                        setNome(nome);
                        setIdade(idade);
                        setCpf(cpf);
                        setCargo(cargo);
                        System.out.println("Recepcionista carregado com sucesso.");
                        return;
                    }
                }
            }
            System.err.println("Recepcionista não encontrado no arquivo.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar recepcionista: " + e.getMessage());
        }
    }
}