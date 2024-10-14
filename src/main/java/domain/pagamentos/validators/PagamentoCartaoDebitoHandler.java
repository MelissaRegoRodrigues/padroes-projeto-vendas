package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
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

    public void validarDadosBasicos() throws Exception {
        if (!numeroCartao.matches("\\d{16}")) {
            throw new Exception("Número do cartão de crédito inválido!");
        }
        if (!codigoSeguranca.matches("\\d{3}")) {
            throw new Exception("Código de segurança inválido!");
        }

    }

}
