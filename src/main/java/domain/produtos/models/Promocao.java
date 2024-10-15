package domain.produtos.models;

import java.time.LocalDateTime;

public class Promocao {

    private Integer id;

    private double desconto;

    private LocalDateTime tempoInicio;

    private LocalDateTime tempoFim;

    public static Promocao SEM_PROMOCAO = new Promocao(0, 0, LocalDateTime.MIN, LocalDateTime.MIN);

    public Promocao(Integer id, double desconto, LocalDateTime tempoInicio, LocalDateTime tempoFim ) {
        this.id = id;
        this.desconto = desconto;
        this.tempoInicio = tempoInicio;
        this.tempoFim = tempoFim;
    }

    public Boolean validar() {
        LocalDateTime dataAtual = LocalDateTime.now();
        return dataAtual.isAfter(tempoInicio) && dataAtual.isBefore(tempoFim);
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
