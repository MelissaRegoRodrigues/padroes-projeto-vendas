package domain.produtos.services;




import domain.produtos.models.Produto;

import java.util.List;

public interface ProdutoService {
    List<Produto> getAllProdutos();
    void addProdutoToCarrinho(Produto produto);
    List<Produto> getCarrinho();
    void deleteProdutoFromCarrinho(Produto produto);
    void buyCarrinho();
}
