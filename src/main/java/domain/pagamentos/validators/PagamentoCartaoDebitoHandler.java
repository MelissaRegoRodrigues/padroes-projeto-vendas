package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosCartaoCredito;
import domain.pagamentos.models.dados.DadosCartaoDebito;
import infrastructure.apis.bandeiras.BandeiraAPI;

public class PagamentoCartaoDebitoHandler extends PagamentoHandler {

    BandeiraAPI bandeiraAPI;

    public PagamentoCartaoDebitoHandler(BandeiraAPI bandeiraAPI) {
        this.bandeiraAPI = bandeiraAPI;
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
            if (!cartao.getNumeroCartao().matches("\\d{16}")) {
                throw new RuntimeException("Número do cartão de crédito inválido!");
            }
            if (!cartao.getCodigoSeguranca().matches("\\d{3}")) {
                throw new RuntimeException("Código de segurança inválido!");
            }
        }
    }
}
