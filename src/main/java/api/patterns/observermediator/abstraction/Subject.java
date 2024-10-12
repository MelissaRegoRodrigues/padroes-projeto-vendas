package api.patterns.observermediator.abstraction;

public interface Subject<T> {

    void anexar(Observer<T> observer);

    void desanexar(Observer<T> observer);

    void notificar(T dados);

}
