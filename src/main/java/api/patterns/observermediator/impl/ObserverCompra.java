package api.patterns.observermediator.impl;

import api.patterns.observermediator.abstraction.Observer;

public class ObserverCompra implements Observer<CompraInfo> {

    @Override
    public void operar(CompraInfo dados) {
        System.out.println("NOTIFICAÇÃO: Você acabou de realizar uma compra de um produto" + dados.nome() +
            " por R$" + dados.preco());
    }

}
