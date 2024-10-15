package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosBoleto;
import infrastructure.apis.banco.BancoAPI;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoBoletoHandler extends PagamentoHandler {

    protected PagamentoBoletoHandler(BancoAPI bancoAPI) {
        super(bancoAPI);
    }

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
        if(pagamento.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Valor deve ser maior que zero");
        }
        if (boleto.getLinkBoleto() == null) {
            throw new RuntimeException("Link Boleto deve ser definido");
        }
        if (boleto.getDataPagamento() == null || boleto.getDataPagamento().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Data de Pagamento inválida!");
        }
    }
}
