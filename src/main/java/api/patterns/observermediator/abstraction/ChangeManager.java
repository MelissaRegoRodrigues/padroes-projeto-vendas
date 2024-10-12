package api.patterns.observermediator.abstraction;

public interface ChangeManager {

    <T> void adicionarObserver(Subject<T> subject, Observer<T> observer);

    <T> void removerObserver(Subject<T> subject, Observer<T> observer);

    <T> void notificar(Subject<T> subject, T dados);

}
