package Funcionarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Auxiliar extends Chefe implements AcoesFuncionarios {

    Scanner scanner = new Scanner(System.in);

    public Auxiliar(int id, String nome, int idade , String cpf, String cargo){
        super(id, nome, idade, cpf, cargo);   
     }   

     public String toCSV() {
        return String.format("Id: %d; Nome: %s; Idade: %d; CPF: %s; Cargo: %s",
                getId(), getNome(), getIdade(), getCpf(), getCargo());
    }
    

    public static void cadastrarAuxiliar(Scanner scanner, List<Auxiliar> auxiliares) {
        System.out.print("Digite o id do auxiliar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o nome do auxiliar: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a idade do auxiliar: ");
        int idade = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Digite o CPF do auxiliar: ");
        String cpf = scanner.nextLine();

        String cargo = "Auxiliar";

        Auxiliar auxiliar = new Auxiliar(id, nome, idade, cpf, cargo);
        auxiliares.add(auxiliar);
    }


    
        @Override
        public void realizarTarefa() {
            System.out.println("O auxiliar está realizando tarefas administrativas.");
        }
        
        @Override
        public void atenderCliente() {
            System.out.println("O auxiliar está atendendo as necessidades dos clientes.");
        }
        
        @Override
        public void organizarEvento() {
            System.out.println("O auxiliar está ajudando na organização de um evento.");
        }
    
        public void salvar(String nomeArquivo) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
                writer.write(String.format("%d;%s;%d;%s;%s\n", getId(), getNome(), getIdade(), getCpf(), getCargo()));
                System.out.println("Auxiliar salvo com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao salvar auxiliar: " + e.getMessage());
            }
        }

        public static void salvarAuxiliares(String nomeArquivo, List<Auxiliar> auxiliares) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (Auxiliar auxiliar : auxiliares) {
                    writer.write(auxiliar.toCSV()); 
                    writer.newLine(); 
                }
                System.out.println("Auxiliares salvos com sucesso em " + nomeArquivo);
            } catch (IOException e) {
                System.err.println("Erro ao salvar auxiliares: " + e.getMessage());
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
                            System.out.println("Auxiliar carregado com sucesso.");
                            return;
                        }
                    }
                }
                System.err.println("Auxiliar não encontrado no arquivo.");
            } catch (IOException | NumberFormatException e) {
                System.err.println("Erro ao carregar auxiliar: " + e.getMessage());
            }
        }
    }

