package api.models;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class Carrinho {
    private List<Produto> produtos;
    private Set<Cupom> cupons;

    public Carrinho(List<Produto> produtos, Set<Cupom> cupons) {
        this.produtos = produtos;
        this.cupons = cupons;
    }

    public Carrinho adicionarProduto(Produto produto){
        this.produtos.add(produto);
        return this;
    }
    public Carrinho adicionarCupom(Cupom cupom){
        this.cupons.add(cupom);
        return this;
    }
    public Carrinho removerCupom(Cupom cupom){
        this.cupons.remove(cupom);
        return this;
    }
    public Carrinho removerProduto(Produto produto){
        this.produtos.remove(produto);
        return this;
    }
//    public BigDecimal calcularPreco(){
//        BigDecimal precoTotal = BigDecimal.ZERO;
//        BigDecimal precoBruto = BigDecimal.ZERO;
//
//        for(Produto produto : produtos){
//            precoBruto = precoBruto.add(produto.getPreco());
//        }
//
//
//    }
}
