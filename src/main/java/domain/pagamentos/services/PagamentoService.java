package domain.pagamentos.services;

import java.math.BigDecimal;

public interface PagamentoService {

    boolean pagar(BigDecimal valor);

}
