package api.models.pagamento.instituicoes;

import java.util.Random;

public class BancoBrasil {

    static public StatusPagamento solicitarAPI() {
        Random random = new Random();
        int status = random.nextInt(0, 3);

        return StatusPagamento.values()[status];
    }
}
