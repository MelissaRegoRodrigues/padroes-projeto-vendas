package domain.pagamentos.services;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.services.strategies.*;
import domain.pagamentos.validators.*;
import infrastructure.apis.banco.BancoBrasilAPI;
import utils.terminal.BetterInputs;

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
    public boolean pagar(BigDecimal valor) {

        ContextoEstrategiaPagamento estrategiaPagamento = new ContextoEstrategiaPagamento();
        Pagamento pagamento = new Pagamento(valor);

        int opcao = BetterInputs.getIntFromEnumeratedValues("Escolha a forma de pagamento: ",
                "Cartão de Crédito", "Cartão de Débito", "Pix", "Boleto");

        switch (opcao) {
            case 1 -> estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoCredito());
            case 2 -> estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoCartaoDebito());
            case 3 -> estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoPix());
            case 4 -> estrategiaPagamento.setEstrategiaPagamento(new EstrategiaPagamentoBoleto());
        }

        estrategiaPagamento.executarEstrategiaPagamento(pagamento);

        try {
            handlers.processar(pagamento);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
