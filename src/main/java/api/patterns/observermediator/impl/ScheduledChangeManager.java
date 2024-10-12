package api.patterns.observermediator.impl;

import api.patterns.observermediator.abstraction.ChangeManager;
import api.patterns.observermediator.abstraction.Observer;
import api.patterns.observermediator.abstraction.Subject;

import java.io.Closeable;
import java.util.*;
import java.util.concurrent.*;

public class ScheduledChangeManager implements ChangeManager, Closeable {

    // ATRIBUTOS
    private final Map<Subject<?>, List<Observer<?>>> mapping = new HashMap<>();

    private final Queue<Runnable> notificacoes = new ConcurrentLinkedQueue<>();

    private final ScheduledExecutorService schudeler;

    private final ExecutorService pool;


    // MÉTODOS DE ACESSO
    public ScheduledChangeManager(ScheduledExecutorService schudeler, ExecutorService notificacaoPool) {
        this.schudeler = schudeler;
        this.pool = notificacaoPool;
    }


    // MÉTODOS
    public void iniciar() {
        schudeler.scheduleAtFixedRate(() -> {
            while (!notificacoes.isEmpty()) {
                pool.execute(notificacoes.poll());
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

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
        if (schudeler.isTerminated() || pool.isTerminated()) {
            throw new RejectedExecutionException("Não foi possível notificar, pois o scheduledChangeManager" +
                "foi encerrado");
        }

        List<Observer<?>> observers = mapping.get(subject);

        if (observers == null || observers.isEmpty()) return;

        for (Observer<?> observer : observers) {
            notificacoes.add(() -> ((Observer<T>) observer).operar(dados));
        }
    }

    @Override
    public void close() {
        pool.shutdown();
        schudeler.shutdown();
    }

}
