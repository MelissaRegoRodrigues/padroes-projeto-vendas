package api.models;

import java.math.BigDecimal;

public class Produto {

    private Integer codigo;
    private String nome;
    private String descricao;
    private int quantidade;
    private BigDecimal preco;
    private Estoque status;
    private Promocao promocao;



    // se nao tiver promoçao é passar o campo como null
    // nao aplica o builder pq só tem mt pouco parametro opcional, n vale a pena
    public Produto (Integer codigo, String nome, String descricao, int quantidade,
                    BigDecimal preco, Estoque status,
                    Promocao promocao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
        this.promocao = promocao;
    }



    public BigDecimal calcularPreco() {
        if (promocao.validar()){
            BigDecimal precoOrigem = this.preco;
            BigDecimal valorDesconto = precoOrigem.multiply(BigDecimal.valueOf(promocao.getDesconto()/100));
            BigDecimal precoNovo = precoOrigem.subtract(valorDesconto);
            return precoNovo;
        }else {
            return preco;
        }
    }


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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

    public Estoque getStatus() {
        return status;
    }

    public void setStatus(Estoque status) {
        this.status = status;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

}
