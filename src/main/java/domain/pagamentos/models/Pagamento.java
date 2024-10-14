package domain.pagamentos.models;


import domain.pagamentos.models.dados.DadosPagamento;

import java.math.BigDecimal;

public class Pagamento {

    private DadosPagamento dadosPagamento;
    private BigDecimal valor;

    public Pagamento(BigDecimal valor) {
        this.valor = valor;
    }

    public DadosPagamento getDadosPagamento() {
        return dadosPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setDadosPagamento(DadosPagamento dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }
}