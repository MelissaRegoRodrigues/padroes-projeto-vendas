package api.sqlite;
import java.sql.*;

public class SQLiteJDBC {

    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String clientes =  "CREATE TABLE IF NOT EXISTS clientes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "sobrenome TEXT NOT NULL," +
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
        System.out.println("Tabela cliente criada com sucesso");

    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:lojinhaTeste.db");
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String clientes =  "CREATE TABLE IF NOT EXISTS clientes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "sobrenome TEXT NOT NULL," +
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
        System.out.println("Tabela cliente criada com sucesso");
}
}