package api.dao;

import api.models.Promocao;
import api.models.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocaoDAO {

    private Connection connection;

    public PromocaoDAO(Connection connection) {
        this.connection = connection;
    }

    // C
    public void adicionarPromocao(Promocao promocao) throws SQLException {
        String sql = "INSERT INTO promocao (promocaoId, desconto, produto_codigo, tempoInicio, tempoFim) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, promocao.getPromocaoId());
            pstmt.setDouble(2, promocao.getDesconto());
            pstmt.setInt(3, promocao.getProduto().getCodigo());
            pstmt.setTimestamp(4, Timestamp.valueOf(promocao.getTempoInicio()));
            pstmt.setTimestamp(5, Timestamp.valueOf(promocao.getTempoFim()));
            pstmt.executeUpdate();
        }
    }

    // R
    public Promocao buscarPromocaoPorId(Integer promocaoId) throws SQLException {
        String sql = "SELECT * FROM promocao WHERE promocaoId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, promocaoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new ProdutoDAO(connection).buscarProdutoPorCodigo(rs.getInt("produto_codigo"));
                    return new Promocao(
                            rs.getInt("promocaoId"),
                            rs.getDouble("desconto"),
                            produto,
                            rs.getTimestamp("tempoInicio").toLocalDateTime(),
                            rs.getTimestamp("tempoFim").toLocalDateTime()
                    );
                }
            }
        }
        return null;
    }

    public List<Promocao> listarTodasPromocoes() throws SQLException {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM promocao";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new ProdutoDAO(connection).buscarProdutoPorCodigo(rs.getInt("produto_codigo"));
                Promocao promocao = new Promocao(
                        rs.getInt("promocaoId"),
                        rs.getDouble("desconto"),
                        produto,
                        rs.getTimestamp("tempoInicio").toLocalDateTime(),
                        rs.getTimestamp("tempoFim").toLocalDateTime()
                );
                promocoes.add(promocao);
            }
        }
        return promocoes;
    }

    // U
    public void atualizarPromocao(Promocao promocao) throws SQLException {
        String sql = "UPDATE promocao SET desconto = ?, produto_codigo = ?, tempoInicio = ?, tempoFim = ? WHERE promocaoId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, promocao.getDesconto());
            pstmt.setInt(2, promocao.getProduto().getCodigo());
            pstmt.setTimestamp(3, Timestamp.valueOf(promocao.getTempoInicio()));
            pstmt.setTimestamp(4, Timestamp.valueOf(promocao.getTempoFim()));
            pstmt.setInt(5, promocao.getPromocaoId());
            pstmt.executeUpdate();
        }
    }

    // D
    public void deletarPromocao(int promocaoId) throws SQLException {
        String sql = "DELETE FROM promocao WHERE promocaoId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, promocaoId);
            pstmt.executeUpdate();
        }
    }

}
