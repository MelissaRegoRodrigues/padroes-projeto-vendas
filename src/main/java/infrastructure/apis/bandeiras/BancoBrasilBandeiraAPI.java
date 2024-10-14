package infrastructure.apis.bandeiras;

import java.util.Random;

public class BancoBrasilBandeiraAPI implements BandeiraAPI {

    @Override
    public StatusPagamento solicitarPagemento() {
        Random random = new Random();
        int status = random.nextInt(0, 3);

        return StatusPagamento.values()[status];
    }

}
