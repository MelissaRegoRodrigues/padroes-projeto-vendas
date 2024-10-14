package domain.pagamentos.models.dados;

import java.time.LocalDateTime;

public class DadosCartaoDebito implements DadosPagamento {

    private String numeroCartao;
    private String codigoSeguranca;
    private LocalDateTime dataPagamento;

    public DadosCartaoDebito() {
    }

    public DadosCartaoDebito(String numeroCartao, String codigoSeguranca, LocalDateTime dataPagamento) {
        this.numeroCartao = numeroCartao;
        this.codigoSeguranca = codigoSeguranca;
        this.dataPagamento = dataPagamento;
    }
    
    @Override
    public String resumo(){
        return "Cartão de Débito com final " + numeroCartao.substring(numeroCartao.length() - 4) ;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
