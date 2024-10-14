package domain.pagamentos.exceptions;

public class PromocaoInvalidaException extends RuntimeException {
    public PromocaoInvalidaException() {
        super("Sua promoção não pode ser aplicada, confira novamente o valor do desconto dado (deve ser menos que 100%)!");
    }
}
