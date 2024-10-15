package domain.pagamentos.validators;

import infrastructure.apis.banco.BancoAPI;
import infrastructure.apis.banco.StatusPagamento;
import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosCartaoCredito;
import infrastructure.utils.TeatroUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PagamentoCartaoCreditoHandler extends PagamentoHandler {

    public PagamentoCartaoCreditoHandler(BancoAPI bancoAPI) {
        super(bancoAPI);
    }

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito) {
            validarDadosBasicos(pagamento);
            System.out.println();
            System.out.printf("Parcelas serão de R$ %.2f", calcularParcelas(pagamento));
            System.out.println("Processando o pagamento com cartão de crédito no valor de " + pagamento.getValor() + " reais...");
            System.out.println();

            TeatroUtils.esperar(5000);
            StatusPagamento status = getBancoAPI().solicitarPagemento();
            while (status == StatusPagamento.PENDENTE) {
                TeatroUtils.esperar(5000);
                System.out.println("O seu pagamento está demorando mais do que o esperado...");
                TeatroUtils.esperar(3000);
                status = getBancoAPI().solicitarPagemento();
            }

            if (status == StatusPagamento.ACEITO) {
                System.out.println("O pagamento foi realizado com sucesso.");
                return true;
            }

            if (status == StatusPagamento.RECUSADO) {
                System.out.println("Infelizmente seu pagamento foi recusado.");
                return false;
            }

        }
        return checarProximo(pagamento);

    }

    @Override
    public void validarDadosBasicos(Pagamento pagamento) throws RuntimeException {
        DadosCartaoCredito cartao = (DadosCartaoCredito) pagamento.getDadosPagamento();
        if(pagamento.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Valor deve ser maior que zero");
        }
        if (!cartao.getNumeroCartao().matches("\\d{16}")) {
            throw new RuntimeException("Número do cartão de crédito inválido!");
        }
        if (!cartao.getCodigoSeguranca().matches("\\d{3}")) {
            throw new RuntimeException("Código de segurança inválido!");
        }
        if (cartao.getQuantidadeParcelas() < 0) {
            throw new RuntimeException("Quantidade de parcelas deve ser maior que zero!");
        }

    }

    private BigDecimal calcularParcelas(Pagamento pagamento){
        int quantidadeParcelas = ((DadosCartaoCredito) pagamento.getDadosPagamento()).getQuantidadeParcelas();
        BigDecimal valorTotal = pagamento.getValor();

        return valorTotal.divide(new BigDecimal(quantidadeParcelas)).setScale(2, RoundingMode.HALF_UP);
    }
}
