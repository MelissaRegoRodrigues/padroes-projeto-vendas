package presentation;

import domain.pagamentos.validators.PagamentoCartaoCreditoHandler;
import domain.pagamentos.validators.PagamentoCartaoDebitoHandler;
import domain.pagamentos.validators.PagamentoHandler;
import domain.pagamentos.services.strategies.ContextoEstrategiaPagamento;
import domain.pagamentos.services.strategies.EstrategiaPagamentoCartaoCredito;
import domain.pagamentos.services.strategies.EstrategiaPagamentoCartaoDebito;
import domain.pagamentos.services.strategies.EstrategiaPagamentoPix;
import domain.pagamentos.models.Pagamento;
import domain.pagamentos.validators.PagamentoPixHandler;
import domain.produtos.models.Carrinho;
import domain.produtos.models.Produto;
import infrastructure.apis.bandeiras.BancoBrasilBandeiraAPI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static List<Produto> produtos = new ArrayList<>();
    private static Carrinho carrinho = new Carrinho();

    // TODO Mudei para injear a API das bandeiras, mas provavelmente cada handler lidará com as bandeiras
    private static PagamentoHandler handlers = PagamentoHandler.encadear(
        new PagamentoCartaoCreditoHandler(new BancoBrasilBandeiraAPI()),
        new PagamentoCartaoDebitoHandler(new BancoBrasilBandeiraAPI()),
        new PagamentoPixHandler());

    public static void main(String[] args) {
        popularProdutos();

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
                    adicionarProdutoAoCarrinho(sc);
                    break;
                case "3":
                    verCarrinho();
                    break;
                case "4":
                    removerProdutoDoCarrinho(sc);
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
        System.out.println("Produtos disponíveis:");
        for (Produto produto : produtos) {
            System.out.println(produto.getId() + " - " + produto.getNome() + ": " + produto.getDescricao() + " - " + produto.getPreco() + "R$");
        }
    }

    private static void adicionarProdutoAoCarrinho(Scanner sc) {
        System.out.println("Digite o código do produto para adicionar ao carrinho:");
        int codigo = Integer.parseInt(sc.nextLine());

        Produto produtoEscolhido = null;
        for (Produto produto : produtos) {
            if (produto.getId() == codigo) {
                produtoEscolhido = produto;
                break;
            }
        }

        if (produtoEscolhido != null) {
            System.out.println("Produto escolhido: " + produtoEscolhido.getNome());
            System.out.println("Digite a quantidade que deseja adicionar:");
            int quantidade = Integer.parseInt(sc.nextLine());

            if (quantidade > 0) {
                carrinho.adicionarProduto(produtoEscolhido, quantidade);
                System.out.println(quantidade + "x " + produtoEscolhido.getNome() + " adicionado(s) ao carrinho.");
            } else {
                System.out.println("Quantidade inválida.");
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void verCarrinho() {
        System.out.println("QUANTIDADE | PRODUTO");

        if (carrinho.getProdutos().isEmpty()) {
            System.out.println("Carrinho está vazio.");
        } else {
            for (Map.Entry<Produto, Integer> entry : carrinho.getProdutos().entrySet()) {
                Produto produto = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println(quantidade + " - " + produto.getNome() + ": " + produto.getPreco() + "R$ (por unidade)");
            }
            System.out.println("Total do carrinho: " + carrinho.calcularPreco() + "R$");
        }
    }

    private static void removerProdutoDoCarrinho(Scanner sc) {
        if (carrinho.getProdutos().isEmpty()) {
            System.out.println("Seu carrinho está vazio!");
            return;
        }
        System.out.println("Digite o código do produto para remover do carrinho:");
        int codigo = Integer.parseInt(sc.nextLine());

        Produto produtoParaRemover = null;
        for (Produto produto : produtos) {
            if (produto.getId() == codigo) {
                produtoParaRemover = produto;
                break;
            }
        }

        if (produtoParaRemover != null && carrinho.getProdutos().containsKey(produtoParaRemover)) {
            System.out.println("Produto selecionado: " + produtoParaRemover.getNome());
            System.out.println("Digite a quantidade que deseja remover:");
            int quantidade = Integer.parseInt(sc.nextLine());

            if (quantidade > 0 || quantidade <= carrinho.getProdutos().get(produtoParaRemover)) {
                carrinho.removerProduto(produtoParaRemover, quantidade);
                System.out.println(quantidade + "x " + produtoParaRemover.getNome() + " removido(s) do carrinho.");
            } else {
                System.out.println("Quantidade inválida.");
            }
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }

    private static void comprarProdutos(Scanner sc) {
        System.out.println("Resumo da compra:");
        verCarrinho();
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Cartão de Débito");
        System.out.println("3 - Pix");

        String opcaoPagamento = sc.nextLine().trim();
        ContextoEstrategiaPagamento estrategiaPagamento = new ContextoEstrategiaPagamento();
        BigDecimal total = carrinho.calcularPreco();
        Pagamento pagamento = new Pagamento(total.doubleValue());

        switch (opcaoPagamento) {
            case "1":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoCredito());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;
            case "2":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoDebito());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;

            case "3":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoPix());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;
            default:
                System.out.println("Método inválido");
        }
        handlers.processar(pagamento);
    }

    private static void popularProdutos() {
        produtos.add(new Produto(1, "Smartphone", "Celular com 128GB", 10, new BigDecimal("2500.00"), null, null));
        produtos.add(new Produto(2, "Notebook", "Notebook Gamer", 5, new BigDecimal("4500.00"), null, null));
        produtos.add(new Produto(3, "Fone de Ouvido", "Fone Bluetooth", 20, new BigDecimal("200.00"), null, null));
    }

}
