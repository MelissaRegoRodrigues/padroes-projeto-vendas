package domain.pagamentos.exceptions;

public class PromocaoInativaException extends RuntimeException {
    public PromocaoInativaException() {
        super("A Promoção não está ativa, confira novamente o prazo da promoção!");
    }
}
