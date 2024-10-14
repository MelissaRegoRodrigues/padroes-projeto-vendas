package infrastructure.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class SQLUtils {

    public static List<String> readFromSQLFile(String path) throws IOException {
        if (!path.endsWith(".sql")) {
            throw new IllegalArgumentException("O caminho especificado \"" + path +
                "\" não aponta para um arquivo SQL");
        }
        String fileContent = Files.readString(Path.of(path));
        return Stream.of(fileContent.split(";"))
            .filter(x -> !x.isBlank())
            .map(String::trim)
            .toList();
    }

}
