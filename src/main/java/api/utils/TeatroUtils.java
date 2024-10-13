package api.utils;

public class TeatroUtils {

    public static void esperar(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}
