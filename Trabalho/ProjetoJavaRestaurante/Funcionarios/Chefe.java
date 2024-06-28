package Funcionarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Main.Pessoa;

public class Chefe extends Pessoa {

    private int id;
    private String cargo;

    public Chefe(int id, String nome, int idade, String cpf, String cargo) {
        super(nome, idade, cpf);
        this.id = id;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Idade: %d | CPF: %s | Cargo: %s",
                id, getNome(), getIdade(), getCpf(), cargo);
    }
    
    public static void cadastrarChefe(Scanner scanner, List<Chefe> chefs) {
        System.out.print("Digite o id do chefe: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do chefe: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do chefe: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o CPF do chefe: ");
        String cpf = scanner.nextLine();

        String cargo = "Chefe";

        Chefe chefe = new Chefe(id, nome, idade, cpf, cargo);
        chefs.add(chefe);
        System.out.println("Chefe cadastrado com sucesso!");
    }

    public static void salvarChefs(String nomeArquivo, List<Chefe> chefs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Chefe chefe : chefs) {
                writer.write(chefe.toString()); 
                writer.newLine(); 
            }
            System.out.println("Chefes salvos com sucesso em " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar chefs: " + e.getMessage());
        }
    }

    public static void carregarChefs(String nomeArquivo, List<Chefe> chefs) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("\\|"); 
                if (dados.length == 5) {
                    int id = Integer.parseInt(dados[0].trim().substring(4)); 
                    String nome = dados[1].trim().substring(7); 
                    int idade = Integer.parseInt(dados[2].trim().substring(7));
                    String cpf = dados[3].trim().substring(6); 
                    String cargo = dados[4].trim().substring(8); 

                    Chefe chefe = new Chefe(id, nome, idade, cpf, cargo);
                    chefs.add(chefe);
                } else {
                    System.out.println("Linha inválida no arquivo: " + linha);
                }
            }
            System.out.println("Dados dos chefes foram carregados do arquivo: " + nomeArquivo);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar chefs: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Erro ao fechar o BufferedReader: " + e.getMessage());
                }
            }
        }
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
