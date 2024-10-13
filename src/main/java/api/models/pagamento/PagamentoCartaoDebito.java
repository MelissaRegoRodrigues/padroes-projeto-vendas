package api.models.pagamento;

import api.models.pagamento.enums.TipoPagamento;
import api.patterns.pagamento.COR.PagamentoHandler;

public class PagamentoCartaoDebito extends PagamentoHandler {
    @Override
    public boolean processar(Pagamento pagamento) {
//        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito) {
//            System.out.println("Processando o pagamento com cartão de débito no valor de " + pagamento.getValor() + " reais.");
//            return true;
//        }
        return checarProximo(pagamento);
    }
}
