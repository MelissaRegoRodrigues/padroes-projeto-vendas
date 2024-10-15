package domain.pagamentos.models.dados;

import java.time.LocalDateTime;

public class DadosPix implements DadosPagamento {

    private String chavePix;
    private LocalDateTime dataPagamento;

    public DadosPix() {
    }

    public DadosPix(String chavePix, LocalDateTime dataPagamento) {
        this.chavePix = chavePix;
        this.dataPagamento = dataPagamento;
    }

    @Override
    public String resumo(){
        return "Chave Pix:" + chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
    public String getChavePix() {
        return chavePix;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }
    public LocalDateTime getDataPagamento() { return dataPagamento; }

}
