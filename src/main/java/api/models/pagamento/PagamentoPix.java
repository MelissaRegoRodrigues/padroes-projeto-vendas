package api.models.pagamento;

import api.models.pagamento.enums.TipoPagamento;
import api.patterns.pagamento.COR.PagamentoHandler;

public class PagamentoPix extends PagamentoHandler {
    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosPix) {
            System.out.println("Processando o pagamento com PIX no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }
}
