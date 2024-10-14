package domain.produtos.models;

import domain.pagamentos.exceptions.PromocaoInativaException;
import domain.pagamentos.exceptions.PromocaoInvalidaException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Promocao {
    private Integer id;
    private double desconto;
    private Produto produto;
    private LocalDateTime tempoInicio;
    private LocalDateTime tempoFim;

    public Promocao(Integer id, double desconto, Produto produto,
                    LocalDateTime tempoInicio, LocalDateTime tempoFim ) {
        this.id = id;
        this.produto = produto;
        this.desconto = desconto;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
    }

    //vai ta validando a promocao para conseguir dale no produto
    public Boolean validar() {

        LocalDateTime dataAtual = LocalDateTime.now();

        if (dataAtual.isAfter(tempoInicio) && dataAtual.isBefore(tempoFim)) {

            if (this.desconto >= 100) {
                throw new PromocaoInvalidaException();
            }

            return true;
        }else{
            throw new PromocaoInativaException();
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
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
