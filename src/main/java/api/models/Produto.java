package api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Produto {

    private String codigo;
    private String nome;
    private String descricao;
    private int quantidade;
    private BigDecimal preco;
    private Estoque status;
    private LocalDate validade;
    private Promocao promocao;
    private Cupom cupomValido;


    // se nao tiver promoçao ou cupom é passar os campos como null
    // nao aplica o builder pq só tem tipo 2 parametros opcionais, n vale a pena
    public Produto (String codigo, String nome, String descricao, int quantidade,
                    BigDecimal preco, Estoque status, LocalDate validade,
                    Promocao promocao, Cupom cupomValido) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
        this.validade = validade;
        this.promocao = promocao;
        this.cupomValido = cupomValido;
    }


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

    public void setStatus(Estoque status) {
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
