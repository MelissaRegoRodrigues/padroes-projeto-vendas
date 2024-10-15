package domain.pagamentos.models.dados;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DadosCartaoCredito implements DadosPagamento {

    private String numeroCartao;
    private String codigoSeguranca;
    private int quantidadeParcelas;
    private LocalDateTime dataPagamento;

    public DadosCartaoCredito() {
    }

    public DadosCartaoCredito(String numeroCartao, String codigoSeguranca, int quantidadeParcelas, LocalDateTime dataPagamento) {
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String resumo() {
        return "Cartão de Crédito com final " + numeroCartao.substring(numeroCartao.length() - 4) ;
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

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

}
