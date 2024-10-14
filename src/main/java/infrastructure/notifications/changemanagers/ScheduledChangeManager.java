package infrastructure.notifications.changemanagers;

import infrastructure.notifications.Observer;
import infrastructure.notifications.Subject;
import java.io.Closeable;
import java.util.*;
import java.util.concurrent.*;

/**
 * {@link ChangeManager} que permite agendar o envio de notificações para enviá-las periódicamente,
 * evitando que notificações em excesso sejam enviadas
 */
public class ScheduledChangeManager implements ChangeManager, Closeable {

    // ATRIBUTOS
    private final Map<Subject<?>, List<Observer<?>>> vinculos = new HashMap<>();

    private final Queue<Runnable> queueNotificacoes = new ConcurrentLinkedQueue<>();

    private final ScheduledExecutorService agendador;

    private final ExecutorService enviador;


    // MÉTODOS DE ACESSO

    /**
     * Cria um {@link ScheduledChangeManager} ainda não inicializado
     * @param agendador será utilizado para agendar o envio de notificações periódicamente
     * @param enviador será utilizado para enviar as notificações em paralelamente
     */
    public ScheduledChangeManager(ScheduledExecutorService agendador, ExecutorService enviador) {
        this.agendador = agendador;
        this.enviador = enviador;
    }


    // MÉTODOS
    /**
     * Inicializa este {@link ScheduledChangeManager}
     * @param esperaInicio tempo de espera para o primeiro envio das notificações, após iniciar
     * @param intervalo tempo de espera entre cada envio de notificações, após a primeira execução
     * @param medida unidade de medida de tempo dos outros dois argumentos
     */
    public void iniciar(Long esperaInicio, Long intervalo, TimeUnit medida) {
        agendador.scheduleAtFixedRate(() -> {
            while (!queueNotificacoes.isEmpty()) {
                enviador.execute(queueNotificacoes.poll());
            }
        }, esperaInicio, intervalo, medida);
    }

    /**
     * Encerra este {@link ScheduledChangeManager}. Após encerrado, ele <b>não poderá ser mais utilizado</b>
     */
    @Override
    public void close() {
        enviador.shutdown();
        agendador.shutdown();
    }


    @Override
    public <T> void adicionarObserver(Subject<T> subject, Observer<T> observer) {
        vinculos.computeIfAbsent(subject, x -> new ArrayList<>()).add(observer);
    }

    @Override
    public <T> void removerObserver(Subject<T> subject, Observer<T> observer) {
        List<Observer<?>> observers = vinculos.get(subject);

        if (observers != null) {
            observers.remove(observer);
        }
    }

    /**
     * Adiciona notificações dos observers desse subject na fila de espera para que eles sejam notificados
     * no próximo envio agendado
     * @param subject que está enviando a notificação
     * @param dados que estão sendo enviados na notificação para os observers
     * @param <T> tipo do objeto que será enviado na notificação
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> void notificar(Subject<T> subject, T dados) {
        if (agendador.isTerminated() || enviador.isTerminated()) {
            throw new RejectedExecutionException("Não foi possível notificar, pois o scheduledChangeManager" +
                "foi encerrado");
        }

        List<Observer<?>> observers = vinculos.get(subject);

        if (observers == null || observers.isEmpty()) return;

        for (Observer<?> observer : observers) {
            /* Envelopa a chamada do método operar em um lambda Runnable para que ele seja
             * chamado apenas no tempo agendado em outra thread */
            queueNotificacoes.add(() -> ((Observer<T>) observer).operar(dados));
        }
    }

}
