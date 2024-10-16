package infrastructure.notifications.impl;

import infrastructure.notifications.Observer;

public class ObserverCompra implements Observer<CompraInfo> {

    @Override
    public void operar(CompraInfo dados) {
        System.out.printf("%nNOTIFICAÇÃO: Você acabou de realizar uma compra de um produto por R$ %.2f %n", dados.preco());
    }

}
