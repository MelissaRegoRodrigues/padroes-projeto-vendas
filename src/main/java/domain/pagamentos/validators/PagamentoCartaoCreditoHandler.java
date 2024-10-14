package domain.pagamentos.validators;

import infrastructure.apis.bandeiras.BandeiraAPI;
import infrastructure.apis.bandeiras.StatusPagamento;
import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosCartaoCredito;
import infrastructure.utils.TeatroUtils;

public class PagamentoCartaoCreditoHandler extends PagamentoHandler {

    private BandeiraAPI bandeiraAPI;

    /* TODO provavelmente cada bandeira se tornará um handler, mas mudei apenas pra não dar
     * problema na hora de compilar*/
    public PagamentoCartaoCreditoHandler(BandeiraAPI bandeiraAPI) {
        this.bandeiraAPI = bandeiraAPI;
    }

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito) {
            System.out.println("Processando o pagamento com cartão de crédito no valor de " + pagamento.getValor() + " reais.");

            TeatroUtils.esperar(5000);
            StatusPagamento status = bandeiraAPI.solicitarPagemento();
            while (status == StatusPagamento.PENDENTE) {
                TeatroUtils.esperar(5000);
                System.out.println("O seu pagamento está demorando mais do que o esperado...");
                TeatroUtils.esperar(3000);
                status = bandeiraAPI.solicitarPagemento();
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
