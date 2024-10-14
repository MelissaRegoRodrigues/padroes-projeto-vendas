package domain.pagamentos.services.strategies;

import domain.pagamentos.models.Pagamento;

public interface EstrategiaPagamento {
    void processarPagamento(Pagamento pagamento);
}
