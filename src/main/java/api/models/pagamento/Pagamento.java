package api.models.pagamento;



public class Pagamento {
    private Integer id;
    private DadosPagamento dadosPagamento;
    private Double valor;

    public Pagamento(Double valor) {
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public DadosPagamento getDadosPagamento() {
        return dadosPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setDadosPagamento(DadosPagamento dadosPagamento) {
        this.dadosPagamento = dadosPagamento;
    }
}