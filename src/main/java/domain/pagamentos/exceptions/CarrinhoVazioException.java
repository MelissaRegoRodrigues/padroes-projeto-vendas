package domain.pagamentos.exceptions;

public class CarrinhoVazioException extends RuntimeException{
    public CarrinhoVazioException(){
        super("Seu carrinho está vazio. Adicione produtos antes de seguir para compra.");
    }
}
