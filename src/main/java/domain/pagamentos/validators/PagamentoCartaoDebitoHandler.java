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

}
