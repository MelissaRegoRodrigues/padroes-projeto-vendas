package api.models.pagamento;

public class DadosCartaoCredito implements DadosPagamento{
    private String numeroCartao;
    private String codigoSeguranca;
    private int quantidadeParcelas;

    public DadosCartaoCredito(String numeroCartao, String codigoSeguranca, int quantidadeParcelas) {
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        if (!numeroCartao.matches("\\d{16}")) {
            throw new Exception("Número do cartão de crédito inválido!");
        }
        if (!codigoSeguranca.matches("\\d{3}")) {
            throw new Exception("Código de segurança inválido!");
        }
        if (quantidadeParcelas < 0) {
            throw new Exception("Quantidade de parcelas deve ser maior que zero!");
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

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

}
