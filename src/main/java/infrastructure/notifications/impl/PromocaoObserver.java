package infrastructure.notifications.impl;

import domain.produtos.models.Promocao;
import infrastructure.notifications.Observer;

public class PromocaoObserver implements Observer<Promocao> {
    @Override
    public void operar(Promocao dados) {
        System.out.println("NOTIFICAÇÃO: Uma  << P R O M O Ç Ã O >> acaba de ser lançada! O desconto é de: "+
                dados.getDesconto() + "%");
    }
}
