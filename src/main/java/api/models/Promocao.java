package api.models;

import api.exceptions.PromocaoInativaException;
import api.exceptions.PromocaoInvalidaException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Promocao {
    private Integer promocaoId;
    private double desconto;
    private Produto produto;
    private LocalDateTime tempoInicio;
    private LocalDateTime tempoFim;

    public Promocao(Integer promocaoId, double desconto, Produto produto,
                    LocalDateTime tempoInicio, LocalDateTime tempoFim ) {
        this.promocaoId = promocaoId;
        this.desconto = desconto;
        this.produto = produto;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
    }

    //vai ta validando a promocao para conseguir dale no produto
    public Boolean validar() {

        LocalDateTime dataAtual = LocalDateTime.now();

        if (dataAtual.isAfter(tempoInicio) && dataAtual.isBefore(tempoFim)) {
            BigDecimal precoOrigem = produto.getPreco();

            if (this.desconto >= 100) {
                throw new PromocaoInvalidaException();
            }

            return true;
        }else{
            throw new PromocaoInativaException();
        }
    }

    public Integer getPromocaoId() {
        return promocaoId;
    }

    public void setPromocaoId(Integer promocaoId) {
        this.promocaoId = promocaoId;
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
