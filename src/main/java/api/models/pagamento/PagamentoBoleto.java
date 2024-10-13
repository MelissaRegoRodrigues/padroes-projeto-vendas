package api.models.pagamento;

import api.patterns.pagamento.COR.PagamentoHandler;

public class PagamentoBoleto extends PagamentoHandler {

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto) {
            System.out.println("Processando o pagamento com boleto no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }
}
