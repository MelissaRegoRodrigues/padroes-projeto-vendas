package domain.pagamentos.services.strategies;

import domain.pagamentos.models.dados.DadosBoleto;
import domain.pagamentos.models.Pagamento;

import java.time.LocalDateTime;

public class EstrategiaPagamentoBoleto implements EstrategiaPagamento{
    DadosBoleto dados = new DadosBoleto();
    @Override
    public void processarPagamento(Pagamento pagamento) {
        dados.setLinkBoleto("https://boletobancario/" + Math.random());
        dados.setDataPagamento(LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(),
                LocalDateTime.now().getDayOfMonth() + 1, LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute()));
        System.out.println(dados.resumo());

        pagamento.setDadosPagamento(dados);
    }
}
