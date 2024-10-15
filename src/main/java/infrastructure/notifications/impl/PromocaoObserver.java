package infrastructure.notifications.impl;

import domain.produtos.services.PromocaoInfo;
import infrastructure.notifications.Observer;

public class PromocaoObserver implements Observer<PromocaoInfo> {

    @Override
    public void operar(PromocaoInfo promocaoInfo) {
        System.out.printf("NOTIFICAÇÃO: Uma  << P R O M O Ç Ã O >> acaba de ser lançada! O desconto de: %d% " +
            "está válido para o produto: %s. O preço original era R$ %.2f e agora está R$ %.2f",
            promocaoInfo.promocao(), promocaoInfo.nomeProduto(), promocaoInfo.precoOriginal(),
            promocaoInfo.getPrecoDescontado());
    }

}
