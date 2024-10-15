package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosCartaoDebito;
import infrastructure.apis.banco.BancoAPI;

import java.math.BigDecimal;

public class PagamentoCartaoDebitoHandler extends PagamentoHandler {


    public PagamentoCartaoDebitoHandler(BancoAPI bancoAPI) {
        super(bancoAPI);
    }

    @Override
    public boolean processar(Pagamento pagamento) {
//        if (pagamento.getDadosPagamento() instanceof DadosCartaoCredito) {
//            System.out.println("Processando o pagamento com cartão de débito no valor de " + pagamento.getValor() + " reais.");
//            return true;
//        }
        return checarProximo(pagamento);
    }

    @Override
    public void validarDadosBasicos(Pagamento pagamento) throws RuntimeException {
        if (pagamento.getDadosPagamento() instanceof DadosCartaoDebito cartao) {
            if(pagamento.getValor().compareTo(BigDecimal.ZERO) > 0){
                throw new RuntimeException("Valor deve ser maior que zero");
            }
            if (!cartao.getNumeroCartao().matches("\\d{16}")) {
                throw new RuntimeException("Número do cartão de crédito inválido!");
            }
            if (!cartao.getCodigoSeguranca().matches("\\d{3}")) {
                throw new RuntimeException("Código de segurança inválido!");
            }
        }
    }
}
