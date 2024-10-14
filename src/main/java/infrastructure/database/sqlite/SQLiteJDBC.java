package infrastructure.database.sqlite;
import java.sql.*;

public class SQLiteJDBC {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");

            stmt = c.createStatement();
            String clientes =  "CREATE TABLE IF NOT EXISTS clientes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "telefone_contato TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "cep TEXT NOT NULL," +
                    "numero INTEGER NOT NULL);";
            stmt.executeUpdate(clientes);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Tabela clientes criada com sucesso");

        //esses daqui são mera suposição

    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");

        stmt = c.createStatement();
        String produtos = "CREATE TABLE IF NOT EXISTS promocoes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao TEXT NOT NULL," +
                "percentual_desconto REAL NOT NULL," +
                "data_inicio TEXT NOT NULL," +
                "data_fim TEXT NOT NULL" +
                ");";
        stmt.executeUpdate(produtos);
        stmt.close();
        c.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
    System.out.println("Tabela promocoes criada com sucesso");


    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");

        stmt = c.createStatement();
        String produtos = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "codigo TEXT NOT NULL," +
                "nome TEXT NOT NULL," +
                "descricao TEXT," +
                "quantidade INTEGER NOT NULL," +
                "preco REAL NOT NULL," +
                "status TEXT NOT NULL," +
                "validade TEXT," +
                "promocao_id INTEGER," +
                "FOREIGN KEY(promocao_id) REFERENCES promocoes(id)" +
                ");";
        stmt.executeUpdate(produtos);
        stmt.close();
        c.close();
    } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
    }
        System.out.println("Tabela produtos criada com sucesso");
}

//adicionar o restante do que for necessário
}