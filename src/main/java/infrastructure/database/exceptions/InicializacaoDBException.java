package infrastructure.database.exceptions;

public class InicializacaoDBException extends RuntimeException {

    public InicializacaoDBException(String message) {
        super(message);
    }

    public InicializacaoDBException(String message, Throwable cause) {
        super(message, cause);
    }

}
