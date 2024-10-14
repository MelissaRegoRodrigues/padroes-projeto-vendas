package domain.pagamentos.models.dados;

import java.time.LocalDateTime;


public class DadosBoleto implements DadosPagamento{

   private String linkBoleto;
   private LocalDateTime dataPagamento;

    public DadosBoleto( String linkBoleto, LocalDateTime dataPagamento) {
        this.linkBoleto = linkBoleto;
        this.dataPagamento = dataPagamento;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        if(linkBoleto==null){
            throw new Exception("Link Boleto deve ser definido");
        }
        if(dataPagamento==null || dataPagamento.isBefore(LocalDateTime.now())){
            throw new Exception("Data de Pagamento inválida!");
        }
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
