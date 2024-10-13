package api.patterns.strategy;

import api.models.pagamento.DadosCartaoCredito;
import api.models.pagamento.Pagamento;

import java.util.Scanner;

public class EstrategiaPagamentoCartao implements EstrategiaPagamento{
    @Override
    public void processarPagamento(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito dados) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Número cartão de crédito: ");
            dados.setNumeroCartao(scanner.next());
            System.out.println("Código de segurança: ");
            dados.setCodigoSeguranca(scanner.next());
            System.out.println("Quantidade de parcelas (mínimo 1): ");
            dados.setQuantidadeParcelas(scanner.nextInt());
            System.out.println("Parcelas serão de R$ " +  Math.round(pagamento.getValor()/(dados.getQuantidadeParcelas())*100)/100.0);

        }
    }
}
