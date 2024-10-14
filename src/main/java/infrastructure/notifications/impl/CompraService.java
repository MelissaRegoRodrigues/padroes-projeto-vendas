package infrastructure.notifications.impl;

import infrastructure.notifications.changemanagers.ChangeManager;
import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;

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
