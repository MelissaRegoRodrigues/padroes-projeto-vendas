package api;

import api.models.pagamento.Pagamento;
import api.models.pagamento.PagamentoCartaoCredito;
import api.models.pagamento.PagamentoCartaoDebito;
import api.models.pagamento.PagamentoPix;
import api.models.pagamento.enums.TipoPagamento;
import api.patterns.pagamento.COR.PagamentoHandler;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        PagamentoHandler handlers = PagamentoHandler.encadear(new PagamentoCartaoCredito(), new PagamentoCartaoDebito(), new PagamentoPix());

        Pagamento pagamentoCredito = new Pagamento(TipoPagamento.CARTAO_CREDITO, BigDecimal.valueOf(100.00));
        Pagamento pagamentoDebito = new Pagamento(TipoPagamento.CARTAO_DEBITO, BigDecimal.valueOf(50.00));
        Pagamento pagamentoPix = new Pagamento(TipoPagamento.PIX, BigDecimal.valueOf(370.41));
        Pagamento pagamentoBitcoin = new Pagamento(TipoPagamento.BITCOIN, BigDecimal.valueOf(1932.29));

        System.out.println("----------- 1 Teste -----------");
        handlers.processar(pagamentoCredito);
        System.out.println("----------- 2 Teste -----------");
        handlers.processar(pagamentoDebito);
        System.out.println("----------- 3 Teste -----------");
        handlers.processar(pagamentoPix);
        System.out.println("----------- 4 Teste -----------");
        handlers.processar(pagamentoBitcoin);

    }
}