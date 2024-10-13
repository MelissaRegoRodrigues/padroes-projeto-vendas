package api.patterns.strategy;

import api.models.pagamento.DadosCartaoCredito;
import api.models.pagamento.Pagamento;

import java.util.Scanner;

public class EstrategiaPagamentoCartaoCredito implements EstrategiaPagamento {
    private DadosCartaoCredito dados = new DadosCartaoCredito();

    @Override
    public void processarPagamento(Pagamento pagamento) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Número cartão de crédito: ");
        dados.setNumeroCartao(scanner.next());
        System.out.println("Código de segurança: ");
        dados.setCodigoSeguranca(scanner.next());
        System.out.println("Quantidade de parcelas (mínimo 1): ");
        dados.setQuantidadeParcelas(scanner.nextInt());
        System.out.println("Parcelas serão de R$ " + Math.round(pagamento.getValor() / (dados.getQuantidadeParcelas()) * 100) / 100.0);

        pagamento.setDadosPagamento(dados);

    }
}
