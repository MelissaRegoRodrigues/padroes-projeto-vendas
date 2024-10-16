package domain.pagamentos.services;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.services.strategies.*;
import domain.pagamentos.validators.*;
import infrastructure.apis.banco.BancoBrasilAPI;
import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;
import infrastructure.notifications.changemanagers.ChangeManager;
import infrastructure.notifications.impl.CompraInfo;
import utils.terminal.BetterInputs;

import java.math.BigDecimal;
import java.util.Scanner;

public class PagamentoServiceImpl implements PagamentoService, Subject<CompraInfo> {

    private final PagamentoHandler handlers = PagamentoHandler.encadear(
            new PagamentoCartaoCreditoHandler(new BancoBrasilAPI()),
            new PagamentoCartaoDebitoHandler(new BancoBrasilAPI()),
            new PagamentoPixHandler(new BancoBrasilAPI()),
            new PagamentoBoletoHandler(new BancoBrasilAPI()));

    private final ChangeManager changeManager;

    private final Scanner sc = new Scanner(System.in);

    public PagamentoServiceImpl(ChangeManager changeManager) {
        this.changeManager = changeManager;
    }

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
            notificar(new CompraInfo(valor));
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    //NOTIFICAÇÕES

    @Override
    public void anexar(Observer<CompraInfo> observer) {
        changeManager.adicionarObserver(this, observer);
    }

    @Override
    public void desanexar(Observer<CompraInfo> observer) {
        changeManager.removerObserver(this, observer);
    }

    @Override
    public void notificar(CompraInfo dados) {
        changeManager.notificar(this, dados);
    }
}
