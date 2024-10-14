package domain.pagamentos.exceptions;

public class TotalInvalidoException extends RuntimeException {
    public TotalInvalidoException() {
        super("O preço total do carrinho não pode ser igual ou menor que 0!");
    }
}
