package domain.produtos.services;

import java.math.BigDecimal;

public record PromocaoInfo(String nomeProduto, int promocao, BigDecimal precoOriginal) {

    public BigDecimal getPrecoDescontado() {
        return precoOriginal.multiply(BigDecimal.valueOf(1 - (promocao/100.00)));
    }

}
