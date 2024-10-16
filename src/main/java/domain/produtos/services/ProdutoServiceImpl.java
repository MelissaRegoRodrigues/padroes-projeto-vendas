package domain.produtos.services;

import domain.pagamentos.services.PagamentoService;
import domain.produtos.daos.ProdutoDAO;
import domain.produtos.models.Carrinho;
import domain.produtos.models.Produto;
import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;
import infrastructure.notifications.changemanagers.ChangeManager;
import utils.terminal.BetterInputs;
import utils.terminal.BetterPrint;
import utils.Pair;
import utils.terminal.tabelas.TablePrinter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.*;
import static domain.produtos.models.Estoque.INDISPONIVEL;

public class ProdutoServiceImpl implements Subject<PromocaoInfo> {

    private final ProdutoDAO produtoDAO;

    private final Carrinho carrinho;

    private final PagamentoService pagamentoService;

    private final ChangeManager changeManager;

    public ProdutoServiceImpl(Connection connection, ChangeManager changeManager,
                              PagamentoService pagamentoService) {
        this.produtoDAO = new ProdutoDAO(connection); // Inicializa o DAO com a conexão
        this.changeManager = changeManager;
        this.carrinho = new Carrinho();
        this.pagamentoService = pagamentoService;
    }


    public void mostrarTodosProdutos() {
        List<Produto> produtos = produtoDAO.listarTodosProdutos();

        TablePrinter.printTable(produtos,
            column1 -> column1.header("ID").with(produto -> produto.getId().toString()),
            column2 -> column2.header("PRODUTO").with(Produto::getNome),
            column3 -> column3.header("QTD. ESTOQUE").with(produto -> String.valueOf(produto.getQuantidade())),
            column4 -> column4.header("PREÇO (R$)").with(produto -> String.format("%.2f", produto.getPreco())),
            column5 -> column5.header("PROMOÇÃO").with(produto -> produto.getPromocao() + "%"),
            column6 -> column6.header("PREÇO (R$) DESC.").with(produto -> "%.2f".formatted(produto.calcularPreco()))
        );
    }

    public void adicionarProdutoAoCarrinho() {
        int id = BetterInputs.prepareIO().newIntInputReader()
            .withMinVal(0)
            .read("Fornça o ID do produto que você deseja adicionar ao carrinho (0 para voltar)");

        if (id == 0) return;

        Optional<Produto> resultado = produtoDAO.buscarPorId(id);

        if (resultado.isEmpty()) {
            System.out.printf("Não há um produto com o ID %d especificado %n", id);
            return;
        } else if (resultado.get().getStatus() == INDISPONIVEL){
            System.out.printf("O produto de ID %d está indisponível %n", id);
            return;
        }

        Produto produto = resultado.get();

        int qnt = inputUnidades("Quantas unidades do produto %s você deseja adicionar?"
                  .formatted(produto.getNome()));

        int novaQnt = carrinho.adicionarProduto(produto, Math.min(qnt, produto.getQuantidade()));
        System.out.printf("%d unidades do produto %s foram adicionadas ao carrinho %n", qnt, produto.getNome());
        System.out.printf("Agora há %d unidades %n", novaQnt);
    }

    private int inputProdutoIdDoCarrinho(String prompt) {
        return BetterInputs.prepareIO().newIntInputReader()
            .withValueChecker((value, name) -> {
                // Retorno nulo significa que não é para lançar erro
                if (value == 0) return null;
                if (carrinho.getProdutoQnt(value).isEmpty()) return List.of("Nenhum produto com o ID especificado");
                return null;
            })
            .read(prompt);
    }

    private int inputUnidades(String prompt) {
        return BetterInputs.prepareIO().newIntInputReader()
            .withMinVal(1)
            .read(prompt);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void removerProdutoDoCarrinho() {
        if (carrinho.vazio()) {
            System.out.println("Seu carrinho está vazio!");
            return;
        }

        int id = inputProdutoIdDoCarrinho("Informe o ID do produto que você deseja remover (0 para voltar):");

        if (id == 0) return;

        Produto produto = carrinho.getProdutoQnt(id).get().left();

        int qnt = inputUnidades("Quantas unidades do produto %s você deseja remover?"
                  .formatted(produto.getNome()));

        int novaQtd = carrinho.removerProduto(produto, qnt);
        System.out.printf("%d unidades do produto %s foram removidas do carrinho%n", qnt, produto.getNome());
        System.out.printf("Há %d unidades restantes%n", novaQtd);
    }

    public void finalizarCompra() {
        if (carrinho.vazio()) {
            System.out.println("Carrinho está vazio. Adicione produtos antes de comprar.");
            return;
        }

        BigDecimal total = carrinho.calcularPreco();
        printResumoCarrinho(carrinho.getProdutoQnt(), total);

        boolean prosseguir = BetterInputs.getConfirmation("Você deseja prosseguir?", false);

        if (prosseguir) {
            try {
                pagamentoService.pagar(total);
                atualizarEstoque(carrinho.getProdutoQnt());
            } catch (RuntimeException e) {
                System.out.println("Não foi possível finalizar sua compra");
            }
            return;
        }

        System.out.println("Não foi possível finalizar sua compra.");
    }

    private void printResumoCarrinho(List<Pair<Produto, Integer>> produtoQtd, BigDecimal total) {
        BetterPrint.printWithBorder("RESUMO DA COMPRA", "=");
        printProdutosEQuantidade(produtoQtd);
        System.out.printf("TOTAL: %.2f %n", total);
    }

    private void atualizarEstoque(List<Pair<Produto, Integer>> produtoQtd) {
        for (var pair : produtoQtd) {

            int qtd = pair.right();
            Produto produto = pair.left();

            produto.setQuantidade(produto.getQuantidade() - qtd);
            produtoDAO.atualizarProduto(produto);
        }
        carrinho.esvazear();
    }

    private void printProdutosEQuantidade(List<Pair<Produto, Integer>> pairs) {
        TablePrinter.printTable(pairs,
            column1 -> column1.header("QNT").with(pair -> pair.right().toString()),
            column2 -> column2.header("ID").with(pair -> pair.left().getId().toString()),
            column3 -> column3.header("PRODUTO").with(pair -> pair.left().getNome()),
            column4 -> column4.header("PREÇO (R$)").with(pair -> String.format("R$%.2f", pair.left().getPreco()))
        );
    }

    public void verCarrinho() {
        if (carrinho.vazio()) {
            System.out.println("Carrinho está vazio.");
            return;
        }

        printProdutosEQuantidade(carrinho.getProdutoQnt());

        System.out.printf("Total do carrinho: R$%.2f %n", carrinho.calcularPreco());
    }

    public void addPromocao() {
        int idProduto = BetterInputs.prepareIO().newIntInputReader()
            .withMinVal(0)
            .read("Informe o ID do produto ao qual você deseja aplicar o desconto (0 para voltar)");

        if (idProduto == 0) return;

        Optional<Produto> resultado = produtoDAO.buscarPorId(idProduto);

        if (resultado.isEmpty()) {
            System.out.printf("Não existe um produto com o ID %d especificado %n", idProduto);
            return;
        } else if (resultado.get().getStatus() == INDISPONIVEL) {
            System.out.printf("O produto %s com ID %d não está disponível no momento %n",
                resultado.get().getNome(), resultado.get().getId());
            return;
        }

        int promocao = BetterInputs.prepareIO().newIntInputReader()
            .withMinVal(1)
            .withMaxVal(99)
            .read("Informe o valor da promoção em formato inteiro no intervalo de 1 a 99: ");

        resultado.get().setPromocao(promocao);
        produtoDAO.atualizarProduto(resultado.get());
        System.out.printf("O desconto de %d foi aplicado ao produto %s! %n", promocao, resultado.get().getNome());
        notificar(new PromocaoInfo(resultado.get().getNome(),promocao,resultado.get().getPreco()));
    }


    // NOTIFICAÇÕES
    @Override
    public void anexar(Observer<PromocaoInfo> observer) {
        changeManager.adicionarObserver(this, observer);
    }

    @Override
    public void desanexar(Observer<PromocaoInfo> observer) {
        changeManager.removerObserver(this, observer);
    }

    @Override
    public void notificar(PromocaoInfo dados) {
        changeManager.notificar(this, dados);
    }

}