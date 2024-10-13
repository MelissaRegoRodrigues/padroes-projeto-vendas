package api.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Carrinho {

    private Map<Produto, Integer> produtos;  // Alterado para mapear produto e sua quantidade

    public Carrinho() {
        this.produtos = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        produtos.put(produto, produtos.getOrDefault(produto, 0) + quantidade);
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (produtos.containsKey(produto)) {
            int qtdAtual = produtos.get(produto);
            if (qtdAtual <= quantidade) {
                produtos.remove(produto);
            } else {
                produtos.put(produto, qtdAtual - quantidade);
            }
        }
    }

    public Map<Produto, Integer> getProdutos() {
        return produtos;
    }

    public BigDecimal calcularPreco() {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Produto, Integer> entry : produtos.entrySet()) {
            total = total.add(entry.getKey().getPreco().multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return total;
    }
}
