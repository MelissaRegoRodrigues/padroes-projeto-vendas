package api.patterns.strategy;

import api.models.pagamento.Pagamento;

public class ContextoEstrategiaPagamento {
    private EstrategiaPagamento estrategiaPagamento;

    public void setEstrategiaPagamento(EstrategiaPagamento estrategiaPagamento) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public void executarEstrategiaPagamento(Pagamento pagamento) {
        if (estrategiaPagamento == null) {
            throw new NullPointerException("tipo de pagamento não definido (null)");
        }
        estrategiaPagamento.processarPagamento(pagamento);
    }

    public EstrategiaPagamento getEstrategiaPagamento() {
        return estrategiaPagamento;
    }
}
