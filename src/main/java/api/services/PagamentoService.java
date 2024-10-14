package api.services;


import api.models.Carrinho;
import api.models.Produto;

import java.util.List;

public interface PagamentoService {
    List<Produto> getAllProdutos();
    void addProdutoToCarrinho(Produto produto);
    List<Produto> getCarrinho();
    void deleteProdutoFromCarrinho(Produto produto);
    void buyCarrinho();
}
