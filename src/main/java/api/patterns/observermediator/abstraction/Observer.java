package api.patterns.observermediator.abstraction;

/**
 * Responsável por receber notificações realizadas por subjects que os interessem
 * para realizar alguma operação
 * @param <T> tipo do objeto que o observer receberá ao ser notificado
 */
public interface Observer<T> {

    /**
     * Opera sobre os dados recebidos pela notificação do subject
     * @param dados objeto com os dados daquela notificação
     */
    void operar(T dados);

}
