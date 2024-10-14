package domain.pagamentos.services;

import domain.pagamentos.services.strategies.EstrategiaPagamento;

public interface PagamentoService {

    void pagar(EstrategiaPagamento estrategiaPagamento);

}
