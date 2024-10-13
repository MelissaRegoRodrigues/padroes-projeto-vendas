package api.patterns.strategy;

import api.models.pagamento.Pagamento;

interface EstrategiaPagamento {
    void processarPagamento(Pagamento pagamento);
}
