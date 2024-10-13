package api.models.pagamento;

public class DadosPix implements DadosPagamento{
    private String chavePix;

    public DadosPix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void validarDadosBasicos() throws Exception {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new Exception("Chave Pix inválida!");
        }
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }


    public String getChavePix() {
        return chavePix;
    }

}
