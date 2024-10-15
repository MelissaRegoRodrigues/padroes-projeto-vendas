package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosCartaoCredito;
import domain.pagamentos.models.Pagamento;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class EstrategiaPagamentoCartaoCredito implements EstrategiaPagamento {


    @Override
    public void processarPagamento(Pagamento pagamento) {
        DadosCartaoCredito dados = new DadosCartaoCredito();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Número cartão de crédito: ");
        dados.setNumeroCartao(scanner.next());
        System.out.println("Código de segurança: ");
        dados.setCodigoSeguranca(scanner.next());
        System.out.println("Quantidade de parcelas (mínimo 1): ");
        dados.setQuantidadeParcelas(scanner.nextInt());
        pagamento.setDadosPagamento(dados);
        System.out.printf("Parcelas serão de R$ %.2f", calcularParcelas(pagamento));
    }

    private BigDecimal calcularParcelas(Pagamento pagamento){
        int quantidadeParcelas = ((DadosCartaoCredito) pagamento.getDadosPagamento()).getQuantidadeParcelas();
        BigDecimal valorTotal = pagamento.getValor();

        return valorTotal.divide(new BigDecimal(quantidadeParcelas), RoundingMode.HALF_UP);
    }
}
