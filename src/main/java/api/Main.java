package api;

import api.models.pagamento.*;

import api.models.pagamento.enums.TipoPagamento;
import api.patterns.pagamento.COR.PagamentoHandler;


public class Main {
    public static void main(String[] args) {

        PagamentoHandler handlers = PagamentoHandler.encadear( new PagamentoCartaoDebito(), new PagamentoPix());

        Pagamento pagamentoCredito = new Pagamento(TipoPagamento.CARTAO_CREDITO,
                new DadosCartaoCredito(
                        "12345678910111213141516", "123", 5
                ), 100.00);

        Pagamento pagamentoDebito = new Pagamento(TipoPagamento.CARTAO_DEBITO,
                new DadosCartaoCredito(
                        "12345678910111213141516", "123", 2
                ), 50.00);

        Pagamento pagamentoPix = new Pagamento(TipoPagamento.PIX, new DadosPix("123"), 370.41);

        System.out.println("----------- 1 Teste -----------");
        handlers.processar(pagamentoCredito);
        System.out.println("----------- 2 Teste -----------");
        handlers.processar(pagamentoDebito);
        System.out.println("----------- 3 Teste -----------");
        handlers.processar(pagamentoPix);

    }
}