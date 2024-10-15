package domain.pagamentos.validators;

import domain.pagamentos.models.Pagamento;
import domain.pagamentos.models.dados.DadosPix;
import infrastructure.apis.banco.BancoAPI;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class PagamentoPixHandler extends PagamentoHandler {
    private static final String CPF_REGEX = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$";
    private static final String CNPJ_REGEX = "^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$|^\\d{14}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String TELEFONE_REGEX = "^\\+?\\d{0,2}\\s?\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$|^\\d{8,13}$";

    public PagamentoPixHandler(BancoAPI bancoAPI) {
        super(bancoAPI);
    }


    @Override
    public boolean processar(Pagamento pagamento) {
        if (pagamento.getDadosPagamento() instanceof DadosPix) {
            validarDadosBasicos(pagamento);
            System.out.println("Processando o pagamento com PIX no valor de " + pagamento.getValor() + " reais.");
            return true;
        }
        return checarProximo(pagamento);
    }

    @Override
    public void validarDadosBasicos(Pagamento pagamento) throws RuntimeException {
        String chavePix = ((DadosPix) pagamento.getDadosPagamento()).getChavePix();

        if (!Pattern.matches(CPF_REGEX, chavePix) &&
            !Pattern.matches(CNPJ_REGEX, chavePix) &&
            !Pattern.matches(EMAIL_REGEX, chavePix) &&
            !Pattern.matches(TELEFONE_REGEX, chavePix)) {
            throw new RuntimeException("Chave pix inválida");
        }
        if(pagamento.getValor().compareTo(BigDecimal.ZERO) > 0){
            throw new RuntimeException("Valor deve ser maior que zero");
        }


    }
}
