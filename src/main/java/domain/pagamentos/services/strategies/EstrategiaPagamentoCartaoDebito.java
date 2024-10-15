package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosCartaoCredito;
import domain.pagamentos.models.Pagamento;

import java.util.Scanner;

public class EstrategiaPagamentoCartaoDebito implements EstrategiaPagamento {

    @Override
    public void processarPagamento(Pagamento pagamento) {
        Scanner scanner = new Scanner(System.in);
        DadosCartaoCredito dados = new DadosCartaoCredito();

        System.out.println("Número cartão de Débito: ");
        dados.setNumeroCartao(scanner.next());
        System.out.println("Código de segurança: ");
        dados.setCodigoSeguranca(scanner.next());
        System.out.printf("Valor total será de R$ %.2f", pagamento.getValor());

        pagamento.setDadosPagamento(dados);
    }
}
