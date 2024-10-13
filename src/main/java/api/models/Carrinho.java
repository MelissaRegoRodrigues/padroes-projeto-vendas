package api.models;

import api.exceptions.CarrinhoVazioException;
import api.exceptions.TotalInvalidoException;

import java.math.BigDecimal;
import java.util.List;

public class Carrinho {
    private List<Produto> produtos;

    public Carrinho(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Carrinho adicionarProduto(Produto produto){
        this.produtos.add(produto);
        return this;
    }

    public Carrinho removerProduto(Produto produto){
        this.produtos.remove(produto);
        return this;
    }
    public BigDecimal calcularPreco(){

        if (produtos.isEmpty()) {
            throw new CarrinhoVazioException();
        }

        BigDecimal precoTotal = BigDecimal.ZERO;

        for(Produto produto : produtos){
            precoTotal = precoTotal.add(produto.getPreco());
        }

        if ((precoTotal.compareTo(BigDecimal.ZERO) <= 0)){
            throw new TotalInvalidoException();
        }

        return precoTotal;
    }
}
