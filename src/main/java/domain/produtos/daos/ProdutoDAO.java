package domain.produtos.daos;

import domain.produtos.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    // C
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, quantidade, preco, promocao) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setBigDecimal(3, produto.getPreco());
            pstmt.setInt(4, produto.getPromocao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // R
    public Optional<Produto> buscarPorId(Integer id) {
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getBigDecimal("preco"),
                        rs.getInt("promocao")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public List<Produto> listarTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Statement stmt = connection.createStatement()) {
             ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("quantidade"),
                    rs.getBigDecimal("preco"),
                    rs.getInt("promocao")
                );
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return produtos;
    }

    // U
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, quantidade = ?, preco = ?, promocao = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getQuantidade());
            pstmt.setBigDecimal(3, produto.getPreco());
            pstmt.setInt(4, produto.getPromocao());
            pstmt.setInt(5, produto.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // D
    public void deletarProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
