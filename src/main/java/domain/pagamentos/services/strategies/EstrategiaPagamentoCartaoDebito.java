package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosCartaoCredito;
import domain.pagamentos.models.Pagamento;

import java.util.Scanner;

public class EstrategiaPagamentoCartaoDebito implements EstrategiaPagamento {
    private DadosCartaoCredito dados;

    @Override
    public void processarPagamento(Pagamento pagamento) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Número cartão de crédito: ");
        dados.setNumeroCartao(scanner.next());
        System.out.println("Código de segurança: ");
        dados.setCodigoSeguranca(scanner.next());
        dados.setQuantidadeParcelas(1);
        pagamento.setDadosPagamento(dados);
    }
}
