package api.models;

import java.time.LocalDateTime;

public class Promocao {
    private double desconto;
    private Produto produto;
    private LocalDateTime tempoInicio;
    private LocalDateTime tempoFim;

    public void aplicarDesconto() {}
    public void retirarDesconto() {}
    public LocalDateTime calcularDuracao(){
        return null;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDateTime getTempoInicio() {
        return tempoInicio;
    }

    public void setTempoInicio(LocalDateTime tempoInicio) {
        this.tempoInicio = tempoInicio;
    }

    public LocalDateTime getTempoFim() {
        return tempoFim;
    }

    public void setTempoFim(LocalDateTime tempoFim) {
        this.tempoFim = tempoFim;
    }
}
