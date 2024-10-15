package domain.produtos.services;

import domain.produtos.daos.ProdutoDAO;
import domain.produtos.daos.PromocaoDAO;
import domain.produtos.models.Carrinho;
import domain.produtos.models.Produto;
import domain.produtos.models.Promocao;
import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;
import infrastructure.notifications.changemanagers.ChangeManager;
import utils.terminal.BetterInputs;
import utils.terminal.BetterPrint;
import utils.Pair;
import utils.terminal.tabelas.TablePrinter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static domain.produtos.models.Estoque.DISPONIVEL;
import static domain.produtos.models.Estoque.INDISPONIVEL;

public class ProdutoServiceImpl implements Subject<Promocao> {

    private final ProdutoDAO produtoDAO;

    private PromocaoDAO promocaoDAO;

    private Carrinho carrinho;

    private final ChangeManager changeManager;

    public ProdutoServiceImpl(Connection connection, ChangeManager changeManager) {
        this.produtoDAO = new ProdutoDAO(connection); // Inicializa o DAO com a conexão
        this.changeManager = changeManager;
    }


    public void mostrarTodosProdutos() {
        List<Produto> produtos = produtoDAO.listarTodosProdutos();

        TablePrinter.printTable(produtos,
            column1 -> column1.header("ID").with(produto -> produto.getId().toString()),
            column2 -> column2.header("PRODUTO").with(Produto::getNome),
            column3 -> column3.header("PREÇO (R$)").with(produto -> String.format("R$%.2f", produto.getPreco())),
            column4 -> column4.header("PROMOCAO").with(produto -> produto.getPromocao().getDesconto()*100 + "%"),
            column5 -> column5.header("PREÇO (R$) DESC.").with(produto -> produto.calcularPreco().toString())
        );
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void adicionarProdutoAoCarrinho() {
        int id = inputProdutoId("Informe o ID do produto que vocÇe deseja adicionar (0 para voltar)");

        if (id == 0) return;

        Produto produto = carrinho.getProdutoQnt(id).get().left();

        int qnt = inputUnidades("Quantas unidades do produto %s você deseja adicionar?"
                  .formatted(produto.getNome()));

        int novaQnt = carrinho.adicionarProduto(produto, qnt);
        System.out.printf("%d unidades do produto %s foram adicionadas ao carrinho%n", qnt, produto.getNome());
        System.out.printf("Agora há %d unidades", novaQnt);
    }

    private int inputProdutoId(String prompt) {
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

        int id = inputProdutoId("Informe o ID do produto que você deseja remover (0 para voltar):");

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

        List<Pair<Produto, Integer>> disponiveis = new ArrayList<>();
        List<Produto> indisponiveis = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Pair<Produto, Integer> pair : carrinho.getProdutoQnt()) {
            Optional<Produto> produto = produtoDAO.buscarPorId(pair.left().getId());

            if (produto.isEmpty() || produto.get().getStatus() == INDISPONIVEL) {
                indisponiveis.add(pair.left());
                continue;
            }

            disponiveis.add(pair);
            total = total.add(produto.get().calcularPreco());
        }

        printResumoCarrinho(disponiveis, indisponiveis, total);

        boolean prosseguir = BetterInputs.getConfirmation("Você deseja prosseguir?", false);

        if (prosseguir) {
            // executa o pagamentoService
        } else {
            return;
        }

        carrinho.esvazear();
    }

    private void printResumoCarrinho(List<Pair<Produto, Integer>> disponiveis, List<Produto> indisponiveis,
                                     BigDecimal total) {
        BetterPrint.printWithBorder("RESUMO DA COMPRA", "=");
        BetterPrint.printWithBorder("Disponíveis", "=");
        printProdutosEQuantidade(disponiveis);
        BetterPrint.printWithBorder("Indisponíveis", "=");
        TablePrinter.printTable(indisponiveis,
            column1 -> column1.header("ID").with(produto -> produto.getId().toString()),
            column2 -> column2.header("PRODUTO").with(Produto::getNome)
        );
        System.out.printf("TOTAL: %.2f %n", total);
    }

    private void atualizarEstoque(List<Pair<Produto, Integer>> produtoQtd) {
        for (var pair : produtoQtd) {
            Produto produto = pair.left();
            int qtd = pair.right();

            produto.setQuantidade(produto.getQuantidade() - qtd);
            produtoDAO.atualizarProduto(produto);
        }
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

        System.out.println("Total do carrinho: " + carrinho.calcularPreco() + "R$");
    }

    // TODO colocar lógica de interação para adicionar promoção
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public void addPromocao(Integer idProduto, Promocao promocao) {

        try{
            promocaoDAO.atualizarPromocao(promocao);
            Produto produtoNoBanco = produtoDAO.buscarPorId(idProduto).get();

            if (produtoNoBanco != null) {

                if (produtoNoBanco.getStatus() == DISPONIVEL) {
                    produtoNoBanco.setPromocao(promocao);
                } else {
                    System.out.println("Produto INDISPONÍVEL para aplicar a promoção.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto para adicionar promoção.", e);
        }
    }


    // NOTIFICAÇÕES
    @Override
    public void anexar(Observer<Promocao> observer) {
        changeManager.adicionarObserver(this, observer);
    }

    @Override
    public void desanexar(Observer<Promocao> observer) {
        changeManager.removerObserver(this, observer);
    }

    @Override
    public void notificar(Promocao dados) {
        changeManager.notificar(this, dados);
    }

}