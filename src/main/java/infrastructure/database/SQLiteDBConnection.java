package infrastructure.database;

import infrastructure.database.exceptions.InicializacaoDBException;
import infrastructure.utils.SQLUtils;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class SQLiteDBConnection {

    private static Connection sqlite;

    public static Connection getConnection() {
        if (sqlite == null) {
            sqlite = createConnection();
        }
        return sqlite;
    }

    private static Connection createConnection() {
        String dbUrl = System.getenv("db_url");
        try {
            return DriverManager.getConnection("jdbc:sqlite:" + dbUrl);
        } catch (SQLException sqlException) {
            throw new RuntimeException("Não foi possível inicializar o SQLite", sqlException);
        }
    }

    public static void createDatabase() {
        List<String> sqlSchema = getSqlSchema();
        try (Statement stmt = sqlite.createStatement()) {
            for (String sql : sqlSchema) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new InicializacaoDBException("Não foi possível inicializar o banco de dados " +
                "sqlite", e);
        }
    }

    public static List<String> getSqlSchema() {
        String schemaPath = System.getenv("schema_path");
        try {
            return SQLUtils.readFromSQLFile(schemaPath);
        } catch (IOException e) {
            throw new InicializacaoDBException("Não foi possível obter o código SQL do " +
                "esquema: " + schemaPath, e);
        }
    }

}
