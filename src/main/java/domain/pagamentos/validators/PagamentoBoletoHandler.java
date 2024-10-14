package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosBoleto;
import domain.pagamentos.models.dados.DadosPagamento;

import java.time.LocalDateTime;

public class PagamentoBoletoHandler extends PagamentoHandler {

    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosBoleto) {
            validarDadosBasicos(pagamento);
            System.out.println("Processando o pagamento com boleto no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }

    @Override
    public void validarDadosBasicos(Pagamento pagamento) throws RuntimeException {
        DadosBoleto boleto = (DadosBoleto) pagamento.getDadosPagamento();
        if (boleto.getLinkBoleto() == null) {
            throw new RuntimeException("Link Boleto deve ser definido");
        }
        if (boleto.getDataPagamento() == null || boleto.getDataPagamento().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Data de Pagamento inválida!");
        }
    }
}
