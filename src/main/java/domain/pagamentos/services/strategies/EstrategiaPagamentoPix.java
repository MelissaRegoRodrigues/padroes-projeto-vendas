package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosPix;
import domain.pagamentos.models.Pagamento;
import org.apache.commons.lang3.RandomStringUtils;

public class EstrategiaPagamentoPix implements EstrategiaPagamento{
    @Override
    public void processarPagamento(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosPix dados) {
            String codigoAleatorio = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
            dados.setChavePix(codigoAleatorio);
            System.out.println("QR Code para chave Pix: " + dados.getChavePix());

            System.out.println("Valor a ser pago: R$ " + pagamento.getValor());
        }
    }
}
