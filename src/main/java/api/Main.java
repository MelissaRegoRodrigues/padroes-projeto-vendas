package api;

import api.patterns.observermediator.impl.CompraService;
import api.patterns.observermediator.impl.ObserverCompra;
import api.patterns.observermediator.impl.ScheduledChangeManager;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var scheduler = Executors.newScheduledThreadPool(5);
        var taskPool = Executors.newFixedThreadPool(5);
        var changeManager = new ScheduledChangeManager(scheduler, taskPool);
        changeManager.iniciar();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Encerrando...");
            changeManager.close();
        }));

        var compraService = new CompraService(changeManager);
        var observerCompra = new ObserverCompra();



        compraService.anexar(observerCompra);
        compraService.comprar("Banana",19.25);

        Thread.sleep(12000);
        compraService.comprar("Maçã", 5.40);
        compraService.comprar("Beringela", 10.50);

        Thread.currentThread().join();
    }
}