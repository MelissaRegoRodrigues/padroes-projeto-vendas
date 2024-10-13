package api.patterns.observermediator.abstraction;

/**
 * Subject que será observado pelos observers e os notificará caso ocorra algum evento deste subject
 * @param <T> tipo do objeto enviado nas notificações e que será manipulado pelos observers
 */
public interface Subject<T> {

    /**
     * Anexa um observer que passará a ser notificado caso ocorra algum evento
     * @param observer que será anexado a este subject
     */
    void anexar(Observer<T> observer);

    /**
     * Desanexa o observer que não mais receberá notificações
     * @param observer que não receberá mais notificações deste subject
     */
    void desanexar(Observer<T> observer);

    /**
     * Notifica todos os observers que estão anexados a este subject
     * @param dados objeto com dados que serão enviados aos observers deste subject
     */
    void notificar(T dados);

}
