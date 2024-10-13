package api.patterns.strategy;

import api.models.pagamento.DadosPix;
import api.models.pagamento.Pagamento;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Scanner;

public class EstrategiaPagamentoPix implements EstrategiaPagamento{
    @Override
    public void processarPagamento(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosPix dados) {
            String codigoAleatorio = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
            dados.setChavePix(codigoAleatorio);
            System.out.println("QR Code para chave Pix: " + dados.getChavePix());
            Scanner scanner = new Scanner(System.in);

            System.out.println("Valor a ser pago: R$ " + pagamento.getValor());
        }
    }
}
