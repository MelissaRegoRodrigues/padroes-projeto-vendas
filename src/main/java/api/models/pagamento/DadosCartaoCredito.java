package api.models.pagamento;

public class DadosCartaoCredito implements DadosPagamento{
    private String numeroCartao;
    private String validade;
    private String codigoSeguranca;
    private int quantidadeParcelas;

    public DadosCartaoCredito(String numeroCartao, String validade, String codigoSeguranca, int quantidadeParcelas) {
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.codigoSeguranca = codigoSeguranca;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        // Validação simples de formato
        if (!numeroCartao.matches("\\d{16}")) {
            throw new Exception("Número do cartão de crédito inválido!");
        }
        if (!validade.matches("\\d{2}/\\d{2}")) {
            throw new Exception("Data de validade inválida!");
        }
        if (!codigoSeguranca.matches("\\d{3}")) {
            throw new Exception("Código de segurança inválido!");
        }
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }
}
