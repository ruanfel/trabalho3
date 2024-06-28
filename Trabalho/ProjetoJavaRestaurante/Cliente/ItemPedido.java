package Cliente;

import java.util.List;
import java.util.Scanner;

public class ItemPedido {
    
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    
    public ItemPedido(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public static void cadastrarItemPedido(Scanner scanner, List<ItemPedido> itensPedido) {
        System.out.print("Digite o id do item de pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome do item de pedido: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o preço do item de pedido: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Digite a quantidade do item de pedido: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); 

        ItemPedido itemPedido = new ItemPedido(id, nome, preco, quantidade);
        itensPedido.add(itemPedido);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
