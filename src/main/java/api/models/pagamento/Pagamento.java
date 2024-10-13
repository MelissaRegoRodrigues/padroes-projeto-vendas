package api.models.pagamento;

public class Pagamento {
    private DadosPagamento dadosPagamento;
    private double valor;

    public Pagamento(DadosPagamento dadosPagamento, double valor) {
        this.dadosPagamento = dadosPagamento;
        this.valor = valor;
    }

    public DadosPagamento getDadosPagamento() {
        return dadosPagamento;
    }

    public double getValor() {
        return valor;
    }
}