package api.patterns.strategy;

import api.models.pagamento.DadosCartaoCredito;
import api.models.pagamento.Pagamento;

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
