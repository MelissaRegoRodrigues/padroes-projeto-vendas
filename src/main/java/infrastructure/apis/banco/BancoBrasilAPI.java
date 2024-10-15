package infrastructure.apis.banco;

import java.util.Random;

public class BancoBrasilAPI implements BancoAPI {

    @Override
    public StatusPagamento solicitarPagemento() {
        Random random = new Random();
        int status = random.nextInt(0, 3);

        return StatusPagamento.values()[status];
    }

}
