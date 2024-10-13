package api.models.pagamento;

import api.models.pagamento.enums.TipoPagamento;

import java.math.BigDecimal;

public class Pagamento {
    private TipoPagamento tipo;
    private BigDecimal valor;

    public Pagamento(TipoPagamento tipo, BigDecimal valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }


    public BigDecimal getValor() {
        return valor;
    }

}
