package api.patterns.strategy;


import api.models.pagamento.DadosBoleto;
import api.models.pagamento.Pagamento;


public class EstrategiaPagamentoBoleto implements EstrategiaPagamento{

    @Override
    public void processarPagamento(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto dados) {
            String linkBoleto = "https://boletobancario/" + Math.random();
            System.out.println("Link para geração do Boleto: " + linkBoleto);
        }
    }
}
