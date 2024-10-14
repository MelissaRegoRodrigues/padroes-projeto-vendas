package domain.produtos.services;

import domain.produtos.daos.ProdutoDAO;
import domain.produtos.models.Estoque;
import domain.produtos.models.Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagamentoServiceImpl implements PagamentoService {

    private ProdutoDAO produtoDAO;
    private List<Produto> carrinho;

    public PagamentoServiceImpl(Connection connection) {
        this.produtoDAO = new ProdutoDAO(connection); // Inicializa o DAO com a conexão
        this.carrinho = new ArrayList<>(); // Inicializa o carrinho como uma lista vazia
    }

    @Override
    public List<Produto> getAllProdutos() {
        try {
            return produtoDAO.listarTodosProdutos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os produtos", e);
        }
    }

    @Override
    public void addProdutoToCarrinho(Produto produto) {
        try {
            Produto produtoNoBanco = produtoDAO.buscarProdutoPorCodigo(produto.getId());

            if (produtoNoBanco != null) {

                if (produtoNoBanco.getStatus() == Estoque.DISPONIVEL) {
                    carrinho.add(produtoNoBanco);
                    System.out.println("Produto adicionado ao carrinho: " + produto.getNome());
                } else {
                    System.out.println("Produto INDISPONÍVEL.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto para adicionar ao carrinho.", e);
        }

    }

    @Override
    public List<Produto> getCarrinho() {
        return List.of();
    }

    @Override
    public void deleteProdutoFromCarrinho(Produto produto) {

    }

    @Override
    public void buyCarrinho() {

    }
}