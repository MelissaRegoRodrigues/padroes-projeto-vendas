package domain.pagamentos.services;

import domain.pagamentos.services.strategies.EstrategiaPagamento;

import java.math.BigDecimal;

public interface PagamentoService {

    void pagar(BigDecimal valor);

}
