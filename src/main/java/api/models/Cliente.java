package api.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class Cliente {
    private long id;
    private String nomeCompleto;
    private String telefoneContato;
    private String email;
    private String CEP;
    private int numero;

    public Cliente(String nome, String telefoneContato, String email, String CEP, int numero) {
        this.nomeCompleto = nome;
        this.telefoneContato = telefoneContato;
        this.email = email;
        this.CEP = CEP;
        this.numero = numero;
    }

    public static Cliente buscarClientePorNome(String nome) {
        Cliente cliente = null; // Inicializa a variável

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM clientes WHERE nome = ?")) {

            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getString("nome"),
                            rs.getString("telefone_contato"),
                            rs.getString("email"),
                            rs.getString("cep"),
                            rs.getInt("numero")
                    );
                }
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", telefoneContato='" + telefoneContato + '\'' +
                ", email='" + email + '\'' +
                ", CEP='" + CEP + '\'' +
                ", numero=" + numero +
                '}';
    }
}
