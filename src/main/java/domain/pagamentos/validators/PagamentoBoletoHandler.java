package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosBoleto;

public class PagamentoBoletoHandler extends PagamentoHandler {

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto) {
            System.out.println("Processando o pagamento com boleto no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }

}
