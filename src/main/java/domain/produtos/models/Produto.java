package domain.produtos.models;

import java.math.BigDecimal;

public class Produto {

    private Integer id;
    private String nome;
    private int quantidade;
    private BigDecimal preco;
    private int promocao;

    public Produto (Integer id, String nome, int quantidade, BigDecimal preco, int promocao) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.promocao = promocao;
    }

    public Produto (Integer id, String nome, int quantidade, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.promocao = 0;
    }


    public BigDecimal calcularPreco() {
        if (promocao != 0) {
            return preco.multiply(BigDecimal.valueOf(1 - (promocao/100.00)));
        }
        return preco;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getPromocao() {
        return promocao;
    }

    public void setPromocao(int promocao) {
        this.promocao = promocao;
    }

    public Estoque getStatus() {
        return quantidade != 0 ? Estoque.DISPONIVEL : Estoque.INDISPONIVEL;
    }

}
