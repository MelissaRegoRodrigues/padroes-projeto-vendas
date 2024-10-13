package api.patterns.pagamento.COR;

import api.models.pagamento.Pagamento;

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
            System.out.println("Não foi possivel processar sua requisição para o tipo de pagamento: " + pagamento.getTipo());
            return true;
        }
        return proximo.processar(pagamento);
    }
}
