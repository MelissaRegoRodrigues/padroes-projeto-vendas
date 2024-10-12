package api.patterns.observermediator.impl;

import api.patterns.observermediator.abstraction.ChangeManager;
import api.patterns.observermediator.abstraction.Observer;
import api.patterns.observermediator.abstraction.Subject;

public class CompraService implements Subject<CompraInfo> {

    ChangeManager changeManager;

    public CompraService(ChangeManager changeManager) {
        this.changeManager = changeManager;
    }

    public void comprar(String produto, double preco) {
        System.out.println("Parabéns, você comprou o produto " + produto + " por R$" + preco);
        notificar(new CompraInfo(produto, preco));
    }

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
