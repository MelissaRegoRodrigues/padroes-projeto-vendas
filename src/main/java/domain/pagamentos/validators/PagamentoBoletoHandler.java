package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosBoleto;

import java.time.LocalDateTime;

public class PagamentoBoletoHandler extends PagamentoHandler {

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto) {
            System.out.println("Processando o pagamento com boleto no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }


    public boolean validarDadosBasicos(DadosBoleto boleto) throws Exception {
        if (boleto.getLinkBoleto() == null) {

            throw new Exception("Link Boleto deve ser definido");

        }
        if (boleto.getDataPagamento() == null || boleto.getDataPagamento().isBefore(LocalDateTime.now())) {
            throw new Exception("Data de Pagamento inválida!");
        }
    }

}
