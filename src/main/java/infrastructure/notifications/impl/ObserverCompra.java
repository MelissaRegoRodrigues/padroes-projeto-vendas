package infrastructure.notifications.impl;

import infrastructure.notifications.Observer;

public class ObserverCompra implements Observer<CompraInfo> {

    @Override
    public void operar(CompraInfo dados) {
        System.out.println("NOTIFICAÇÃO: Você acabou de realizar uma compra de um produto " + dados.nomeProduto() +
            " por R$ " + dados.preco());
    }

}
