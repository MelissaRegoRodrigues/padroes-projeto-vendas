package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosPagamento;

public abstract class PagamentoHandler {
    private PagamentoHandler proximo;

    public static PagamentoHandler encadear(PagamentoHandler primeiro, PagamentoHandler... cadeia) {
        PagamentoHandler cabeca = primeiro;
        for (PagamentoHandler seguinte : cadeia) {
            cabeca.proximo = seguinte;
            cabeca = seguinte;
        }
        return primeiro;
    }

    public abstract boolean processar(Pagamento pagamento);

    protected boolean checarProximo(Pagamento pagamento) {
        if (proximo == null) {
            System.out.println("Não foi possivel processar sua requisição para este tipo de pagamento");
            return false;
        }
        return proximo.processar(pagamento);
    }

     public abstract boolean validarDadosBasicos(DadosPagamento dados) throws Exception;

}
