package domain.pagamentos.services;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.services.strategies.ContextoEstrategiaPagamento;
import domain.pagamentos.services.strategies.EstrategiaPagamentoCartaoCredito;
import domain.pagamentos.services.strategies.EstrategiaPagamentoCartaoDebito;
import domain.pagamentos.services.strategies.EstrategiaPagamentoPix;
import domain.pagamentos.validators.PagamentoCartaoCreditoHandler;
import domain.pagamentos.validators.PagamentoCartaoDebitoHandler;
import domain.pagamentos.validators.PagamentoHandler;
import domain.pagamentos.validators.PagamentoPixHandler;
import infrastructure.apis.banco.BancoBrasilAPI;

import java.math.BigDecimal;
import java.util.Scanner;

public class PagamentoServiceImpl implements PagamentoService {
    private PagamentoHandler handlers = PagamentoHandler.encadear(
            new PagamentoCartaoCreditoHandler(),
            new PagamentoCartaoDebitoHandler(),
            new PagamentoPixHandler());

    private Scanner sc = new Scanner(System.in);

    @Override
    public void pagar(BigDecimal valor) {
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Cartão de Débito");
        System.out.println("3 - Pix");

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
            default:
                System.out.println("Método inválido");
        }
        handlers.processar(pagamento);
    }
}
