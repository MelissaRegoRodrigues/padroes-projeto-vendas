package api.patterns.strategy;

import api.models.pagamento.Pagamento;

public interface EstrategiaPagamento {
    void processarPagamento(Pagamento pagamento);
}
