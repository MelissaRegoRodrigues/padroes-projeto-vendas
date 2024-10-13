package api.models.pagamento;

public class DadosPix implements DadosPagamento{
    private String chavePix;
    private double valor;

    public DadosPix(String chavePix, double valor) {
        this.chavePix = chavePix;
        this.valor = valor;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new Exception("Chave Pix inválida!");
        }
        if (valor <= 0) {
            throw new Exception("Valor do pagamento deve ser positivo!");
        }
    }

    public String getChavePix() {
        return chavePix;
    }

    public double getValor() {
        return valor;
    }
}
