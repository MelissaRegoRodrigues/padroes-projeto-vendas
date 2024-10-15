package utils.terminal;

import domain.produtos.models.Carrinho;
import domain.produtos.models.Produto;
import domain.produtos.models.Promocao;
import utils.terminal.tabelas.TablePrinter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TesteLayoutTerminal {

    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho();

        List<Produto> produtos = Arrays.asList(
            new Produto(1, "Banana", 5, new BigDecimal(555555555555555555.55), null)
        );

        produtos.get(0).setPromocao(new Promocao(1, 0.2, LocalDateTime.now(), LocalDateTime.now()));

        carrinho.adicionarProduto(produtos.get(0), produtos.get(0).getQuantidade());

        BetterPrint.printWithBorder("RESUMO DA COMPRA", "=");
        BetterPrint.printWithBorder("Disponíveis", "~");
        TablePrinter.printTable(carrinho.getProdutoQnt(),
            column1 -> column1.header("QNT").with(pair -> pair.right().toString()),
            column2 -> column2.header("ID").with(pair -> pair.left().getId().toString()),
            column3 -> column3.header("PRODUTO").with(pair -> pair.left().getNome()),
            column4 -> column4.header("PREÇO (R$)").with(pair -> String.format("R$%.2f", pair.left().getPreco()))
        );
        BetterPrint.printWithBorder("Indisponíveis", "~");
        TablePrinter.printTable(carrinho.getProdutoQnt(),
            column1 -> column1.header("QNT").with(pair -> pair.right().toString()),
            column2 -> column2.header("ID").with(pair -> pair.left().getId().toString()),
            column3 -> column3.header("PRODUTO").with(pair -> pair.left().getNome()),
            column4 -> column4.header("PREÇO (R$)").with(pair -> String.format("R$%.2f", pair.left().getPreco()))
        );

        int option = BetterInputs.getIntFromEnumeratedValues("Escolha algo", "banana", "goiaba");

        //System.out.println("A resposta foi: " + answer);
        System.out.println(option);
    }

}
