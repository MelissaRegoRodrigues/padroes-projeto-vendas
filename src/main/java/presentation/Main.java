package presentation;

import domain.pagamentos.services.PagamentoServiceImpl;
import domain.produtos.models.Carrinho;
import domain.produtos.models.Produto;
import domain.produtos.services.ProdutoServiceImpl;
import infrastructure.database.SQLiteDBConnection;
import infrastructure.notifications.changemanagers.ScheduledChangeManager;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.sqlite.SQLiteConnection;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {

    private static PagamentoServiceImpl pagamentoService = new PagamentoServiceImpl();
    private static ProdutoServiceImpl produtoService = new ProdutoServiceImpl(
        SQLiteDBConnection.getConnection(),
        new ScheduledChangeManager(Executors.newScheduledThreadPool(1), Executors.newFixedThreadPool(5)));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcao;
        boolean continuar = true;
        System.out.println("Bem-vindo à nossa loja!");

        do {
            System.out.println("Você é:");
            System.out.println("1 - Cliente");
            System.out.println("2 - Administrador");
            System.out.println("Digite 'sair' para encerrar.");

            opcao = sc.nextLine().toLowerCase().trim();

            switch (opcao) {
                case "1":
                    mostrarMenuCliente(sc);
                    break;
                case "2":
                    System.out.println("Menu do Administrador (a ser implementado)...");
                    break;
                case "sair":
                    System.out.println("Saindo do sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção desconhecida, escolha uma opção entre 1, 2 ou 'sair'.");
            }

            System.out.println();

        } while (continuar);

        sc.close();
    }

    private static void mostrarMenuCliente(Scanner sc) {
        String opcao;
        boolean continuar = true;

        do {
            System.out.println("Menu do Cliente:");
            System.out.println("1 - Ver todos os produtos");
            System.out.println("2 - Adicionar produto ao carrinho");
            System.out.println("3 - Ver carrinho de compras");
            System.out.println("4 - Remover produto do carrinho");
            System.out.println("5 - Comprar produtos no carrinho");
            System.out.println("6 - Voltar");

            opcao = sc.nextLine().toLowerCase().trim();

            switch (opcao) {
                case "1":
                    verTodosProdutos();
                    break;
                case "2":
                    adicionarProdutoAoCarrinho();
                    break;
                case "3":
                    verCarrinho();
                    break;
                case "4":
                    removerProdutoDoCarrinho();
                    break;
                case "5":
                    comprarProdutos(sc);
                    break;
                case "6":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção desconhecida, escolha uma opção válida.");
            }

            System.out.println();

        } while (continuar);
    }

    private static void verTodosProdutos() {
        produtoService.mostrarTodosProdutos();
    }

    private static void adicionarProdutoAoCarrinho() {
        produtoService.adicionarProdutoAoCarrinho();
    }

    private static void verCarrinho() {
        produtoService.verCarrinho();
    }

    private static void removerProdutoDoCarrinho() {
        produtoService.removerProdutoDoCarrinho();
    }

    private static void comprarProdutos(Scanner sc) {
        produtoService.finalizarCompra();
    }

}
