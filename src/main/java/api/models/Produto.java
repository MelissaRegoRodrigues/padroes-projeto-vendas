package api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {

    private String codigo;
    private String nome;
    private String descricao;
    private int quantidade;
    private BigDecimal preco;
    private Enum<Estoque> status;
    private LocalDate validade;
    private Promocao promocao;
    private Cupom cupomValido;

    public void aplicarDesconto() {}

    public void associarCupom() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Enum<Estoque> getStatus() {
        return status;
    }

    public void setStatus(Enum<Estoque> status) {
        this.status = status;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Cupom getCupomValido() {
        return cupomValido;
    }

    public void setCupomValido(Cupom cupomValido) {
        this.cupomValido = cupomValido;
    }
}
