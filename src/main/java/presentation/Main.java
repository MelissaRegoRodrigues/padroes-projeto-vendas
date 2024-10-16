package presentation;

import domain.pagamentos.services.PagamentoServiceImpl;
import domain.produtos.services.ProdutoServiceImpl;
import infrastructure.database.SQLiteDBConnection;
import infrastructure.notifications.changemanagers.ScheduledChangeManager;
import infrastructure.notifications.changemanagers.SimpleChangeManager;
import utils.terminal.BetterInputs;
import utils.terminal.BetterPrint;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static PagamentoServiceImpl pagamentoService;
    private static ProdutoServiceImpl produtoService;

    public static void init(){
        pagamentoService = new PagamentoServiceImpl(new SimpleChangeManager());
        ScheduledChangeManager scm = new ScheduledChangeManager(Executors.newScheduledThreadPool(1),
                Executors.newFixedThreadPool(5));
        scm.iniciar(8L,8L, TimeUnit.SECONDS);
        produtoService = new ProdutoServiceImpl(SQLiteDBConnection.getConnection(), scm, pagamentoService);

    }

    public static void main(String[] args) {

        init();

        SQLiteDBConnection.createDatabase();

        BetterPrint.printWithBorder(" Seja bem-vindo à nossa loja! ", "=");

        boolean continuar = true;
        do {
            int opcao = BetterInputs.getIntFromEnumeratedValues("Você é: ", "Cliente", "Administrador", "Sair");

            switch (opcao) {
                case 1 -> mostrarMenuCliente();
                case 2 -> mostrarMenuAdmin();
                case 3 -> {
                    System.out.println("Saindo do Sistema...");
                    continuar = false;
                }
            }
        } while (continuar);

    }

    private static void mostrarMenuCliente() {
        boolean continuar = true;

        do {
            int opcao = BetterInputs.getIntFromEnumeratedValues(" Menu do Cliente: ",
                "Ver todos os produtos",
                "Adicionar produto ao carrinho",
                "Ver carrinho de compras",
                "Remover produto do carrinho",
                "Comprar produtos no carrinho",
                "Voltar");

            switch (opcao) {
                case 1 -> verTodosProdutos();
                case 2 -> adicionarProdutoAoCarrinho();
                case 3 -> verCarrinho();
                case 4 -> removerProdutoDoCarrinho();
                case 5 -> comprarProdutos();
                case 6 -> continuar = false;
            }
        } while (continuar);
    }

    private static void mostrarMenuAdmin(){
        boolean continuar = true;

        do{
            int opcao = BetterInputs.getIntFromEnumeratedValues(" Menu do Administrador: ",
                    "Ver todos os produtos","Adicionar promoção", "Voltar");
            switch (opcao){
                case 1 -> verTodosProdutos();
                case 2 -> adicionarPromocao();
                case 3 -> continuar = false;
            }
        }while (continuar);

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

    private static void comprarProdutos() {
        produtoService.finalizarCompra();
    }

    private static void adicionarPromocao(){
        produtoService.addPromocao();
    }

}
