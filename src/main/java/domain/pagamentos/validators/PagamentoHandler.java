package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import infrastructure.apis.banco.BancoAPI;
import infrastructure.apis.banco.BancoBrasilAPI;

import java.math.BigDecimal;

public abstract class PagamentoHandler {
    private PagamentoHandler proximo;
    private BancoAPI bancoAPI;

    public PagamentoHandler(BancoAPI bancoAPI) {
        this.bancoAPI = bancoAPI;
    }

    public static PagamentoHandler encadear(PagamentoHandler primeiro, PagamentoHandler... cadeia) {
        PagamentoHandler cabeca = primeiro;
        for (PagamentoHandler seguinte : cadeia) {
            cabeca.proximo = seguinte;
            cabeca = seguinte;
        }
        return primeiro;
    }

    protected BancoAPI getBancoAPI() {
        return bancoAPI;
    }

    public abstract boolean processar(Pagamento pagamento);

    protected boolean checarProximo(Pagamento pagamento) {
        if (proximo == null) {
            System.out.println("Não foi possivel processar sua requisição para este tipo de pagamento");
            return false;
        }
        return proximo.processar(pagamento);
    }

     public abstract void validarDadosBasicos(Pagamento pagamento) throws RuntimeException;

}
