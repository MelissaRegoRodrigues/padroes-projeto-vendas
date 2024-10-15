package domain.produtos.daos;

import domain.produtos.models.Promocao;
import domain.produtos.models.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromocaoDAO {

    private Connection connection;

    public PromocaoDAO(Connection connection) {
        this.connection = connection;
    }

    // C
    public void adicionarPromocao(Promocao promocao, int idProduto){
        String sql = "INSERT INTO promocao (desconto, produto_id, tempoInicio, tempoFim) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, promocao.getDesconto());
            pstmt.setInt(2, idProduto);
            pstmt.setTimestamp(3, Timestamp.valueOf(promocao.getTempoInicio()));
            pstmt.setTimestamp(4, Timestamp.valueOf(promocao.getTempoFim()));
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // R
    public Optional<Promocao> buscarPromocaoPorId(Integer promocaoId) {
        String sql = "SELECT * FROM promocao WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, promocaoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Promocao(
                            rs.getInt("promocaoId"),
                            rs.getDouble("desconto"),
                            rs.getTimestamp("tempoInicio").toLocalDateTime(),
                            rs.getTimestamp("tempoFim").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Promocao> listarTodasPromocoes() {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM promocao";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Optional<Produto> produto = new ProdutoDAO(connection).buscarPorId(rs.getInt("produto_codigo"));
                Promocao promocao = new Promocao(
                        rs.getInt("promocaoId"),
                        rs.getDouble("desconto"),
                        rs.getTimestamp("tempoInicio").toLocalDateTime(),
                        rs.getTimestamp("tempoFim").toLocalDateTime()
                );
                promocoes.add(promocao);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return promocoes;
    }

    // U
    public void atualizarPromocao(Promocao promocao) {
        String sql = "UPDATE promocao SET desconto = ?, tempoInicio = ?, tempoFim = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, promocao.getDesconto());
            pstmt.setTimestamp(2, Timestamp.valueOf(promocao.getTempoInicio()));
            pstmt.setTimestamp(3, Timestamp.valueOf(promocao.getTempoFim()));
            pstmt.setInt(4, promocao.getId());
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // D
    public void deletarPromocao(int promocaoId){
        String sql = "DELETE FROM promocao WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, promocaoId);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
