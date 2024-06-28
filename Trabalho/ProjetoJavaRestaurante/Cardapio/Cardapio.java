package Cardapio;

import java.util.ArrayList;

public class Cardapio {
    public static ArrayList<Prato> pratos;

    public Cardapio() {
        pratos = new ArrayList<>();
    }

    public static void adicionarPrato(String nome, double preco) {
        Prato prato = new Prato(nome, preco);
        pratos.add(prato);
    }

    public static void listarCardapio() {
        System.out.println("Cardápio:");
        for (Prato prato : pratos) {
            System.out.printf("%s - R$%.2f%n", prato.getNome(), prato.getPreco());
        }
    }
}
