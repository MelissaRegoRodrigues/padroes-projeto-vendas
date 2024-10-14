package domain.pagamentos.models.dados;

public class DadosCartaoDebito implements DadosPagamento {

    private String numeroCartao;
    private String codigoSeguranca;
    private final int quantidadeParcelas = 1;

    public DadosCartaoDebito() {
    }

    public DadosCartaoDebito(String numeroCartao, String codigoSeguranca) {
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        if (!numeroCartao.matches("\\d{16}")) {
            throw new Exception("Número do cartão de crédito inválido!");
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

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }


}
