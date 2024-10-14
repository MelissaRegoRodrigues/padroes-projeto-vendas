package infrastructure.notifications.changemanagers;

import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;

/**
 * Mediator que coordena a comunicação entre subjects e observers do sistema, permitindo maior flexibilidade
 * para controlar diferentes tipos de lógica de envio das notificações
 */
public interface ChangeManager {

    /**
     * Vincula um observer a um subject, para que aquele seja notificado quando este libere uma notificação
     * @param subject responsável por notificar os observers
     * @param observer responsável por receber notificações dos subjects
     * @param <T> tipo do objeto que será enviado dentro da notificação do subject e recebido pelo observer
     */
    <T> void adicionarObserver(Subject<T> subject, Observer<T> observer);

    /**
     * Desvincula um observer de um subject para que aquele não mais receba notificações deste
     * @param subject que terá seu observer desvinculado
     * @param observer que não mais receberá notificações do subject fornecido
     * @param <T> tipo do objeto que era enviado nas notificações
     */
    <T> void removerObserver(Subject<T> subject, Observer<T> observer);

    /**
     * Notifica todos os observers vinculados àquele observer em específico, passando também um objeto com
     * informações sobre a notificação
     * @param subject que está enviando a notificação
     * @param dados que estão sendo enviados na notificação para os observers
     * @param <T> tipo do objeto que está sendo enviado na notificação
     */
    <T> void notificar(Subject<T> subject, T dados);

}
