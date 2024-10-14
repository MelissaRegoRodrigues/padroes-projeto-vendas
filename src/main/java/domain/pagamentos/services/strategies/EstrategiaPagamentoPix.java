package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosPix;
import domain.pagamentos.models.Pagamento;

public class EstrategiaPagamentoPix implements EstrategiaPagamento{
    DadosPix dados = new DadosPix();
    @Override
    public void processarPagamento(Pagamento pagamento) {
        String codigoAleatorio = "80.352.321/0004-37";
        dados.setChavePix(codigoAleatorio);
        System.out.println(dados.resumo());
        System.out.println("Valor a ser pago: R$ " + pagamento.getValor());

        pagamento.setDadosPagamento(dados);

    }
}
