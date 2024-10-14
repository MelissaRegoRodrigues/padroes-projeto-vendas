package domain.produtos.services;




import domain.produtos.models.Produto;
import domain.produtos.models.Promocao;

import java.util.List;

public interface ProdutoService {
    List<Produto> getAllProdutos();
    void addProdutoToCarrinho(Produto produto);
    List<Produto> getCarrinho();
    void deleteProdutoFromCarrinho(Produto produto);
    void buyCarrinho();
    void addPromocao(Integer idProduto, Promocao promocao);
}
