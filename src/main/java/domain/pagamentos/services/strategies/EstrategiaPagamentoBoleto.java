package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosBoleto;
import domain.pagamentos.models.Pagamento;

public class EstrategiaPagamentoBoleto implements EstrategiaPagamento{

    @Override
    public void processarPagamento(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto dados) {
            String linkBoleto = "https://boletobancario/" + Math.random();
            System.out.println("Link para geração do Boleto: " + linkBoleto);
        }
    }
}
