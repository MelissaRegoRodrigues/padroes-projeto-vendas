package api.models.pagamento;

import api.models.pagamento.instituicoes.BancoBrasil;
import api.models.pagamento.instituicoes.StatusPagamento;
import api.patterns.pagamento.COR.PagamentoHandler;
import api.utils.TeatroUtils;

public class PagamentoCartaoCredito extends PagamentoHandler {

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito) {
            System.out.println("Processando o pagamento com cartão de crédito no valor de " + pagamento.getValor() + " reais.");

            TeatroUtils.esperar(5000);
            StatusPagamento status = BancoBrasil.solicitarAPI();
            while (status == StatusPagamento.PENDENTE) {
                TeatroUtils.esperar(5000);
                System.out.println("O seu pagamento está demorando mais do que o esperado...");
                TeatroUtils.esperar(3000);
                status = BancoBrasil.solicitarAPI();
            }

            if (status == StatusPagamento.ACEITO) {
                System.out.println("O pagamento foi realizado com sucesso.");
                return true;
            }

            if (status == StatusPagamento.RECUSADO) {
                System.out.println("Infelizmente seu pagamento foi recusado.");
                return false;
            }

        }
        return checarProximo(pagamento);
    }
}
