package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosPix;

public class PagamentoPixHandler extends PagamentoHandler {
    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosPix) {
            System.out.println("Processando o pagamento com PIX no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }
}
