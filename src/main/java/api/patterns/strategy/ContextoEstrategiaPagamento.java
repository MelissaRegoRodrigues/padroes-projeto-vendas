package api.patterns.strategy;

import api.models.pagamento.Pagamento;

public class ContextoEstrategiaPagamento {
    private EstrategiaPagamento estrategiaPagamento;

    public void setEstrategiaPagamento(EstrategiaPagamento estrategiaPagamento) {
        this.estrategiaPagamento = estrategiaPagamento;
    }

    public void executarEstrategiaPagamento(EstrategiaPagamento estrategiaPagamento, Pagamento pagamento){
        estrategiaPagamento.processarPagamento(pagamento);
    }
}
