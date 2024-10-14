package domain.pagamentos.models.dados;

import java.time.LocalDateTime;


public class DadosBoleto implements DadosPagamento{

   private String linkBoleto;
   private LocalDateTime dataPagamento;

   public DadosBoleto(){}

    public DadosBoleto( String linkBoleto, LocalDateTime dataPagamento) {
        this.linkBoleto = linkBoleto;
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String resumo() {
        return "Boleto: " + linkBoleto + "\nData de pagamento: " + dataPagamento;
    }

    public String getLinkBoleto() {
        return linkBoleto;
    }

    public void setLinkBoleto(String linkBoleto) {
        this.linkBoleto = linkBoleto;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


}
