package infrastructure.notifications.changemanagers;

import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link ChangeManager} mais simples que envia todas as notificações que recebe imediatamente
 */
public class SimpleChangeManager implements ChangeManager {

    private final Map<Subject<?>, List<Observer<?>>> mapping = new HashMap<>();

    @Override
    public <T> void adicionarObserver(Subject<T> subject, Observer<T> observer) {
        mapping.computeIfAbsent(subject, x -> new ArrayList<>()).add(observer);
    }

    @Override
    public <T> void removerObserver(Subject<T> subject, Observer<T> observer) {
        List<Observer<?>> observers = mapping.get(subject);

        if (observers != null) {
            observers.remove(observer);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void notificar(Subject<T> subject, T dados) {
        List<Observer<?>> observers = mapping.get(subject);

        if (observers == null) return;

        for (Observer<?> observer : observers) {
            ((Observer<T>) observer).operar( dados);
        }
    }

}
