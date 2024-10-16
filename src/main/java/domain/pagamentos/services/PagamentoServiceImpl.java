package domain.pagamentos.services;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.services.strategies.*;
import domain.pagamentos.validators.*;
import infrastructure.apis.banco.BancoBrasilAPI;

import java.math.BigDecimal;
import java.util.Scanner;

public class PagamentoServiceImpl implements PagamentoService {
    private final PagamentoHandler handlers = PagamentoHandler.encadear(
            new PagamentoCartaoCreditoHandler(new BancoBrasilAPI()),
            new PagamentoCartaoDebitoHandler(new BancoBrasilAPI()),
            new PagamentoPixHandler(new BancoBrasilAPI()),
            new PagamentoBoletoHandler(new BancoBrasilAPI()));

    private final Scanner sc = new Scanner(System.in);

    @Override
    public void pagar(BigDecimal valor) {
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Cartão de Débito");
        System.out.println("3 - Pix");
        System.out.println("4 - Boleto");

        String opcaoPagamento = sc.nextLine().trim();
        ContextoEstrategiaPagamento estrategiaPagamento = new ContextoEstrategiaPagamento();

        Pagamento pagamento = new Pagamento(valor);

        switch (opcaoPagamento) {
            case "1":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoCredito());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;
            case "2":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoDebito());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;

            case "3":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoPix());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;
            case "4":
                estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoBoleto());
                estrategiaPagamento.executarEstrategiaPagamento(pagamento);
                break;
            default:
                System.out.println("Método inválido");
        }
        try {
            handlers.processar(pagamento);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}