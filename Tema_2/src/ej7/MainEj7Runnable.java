package ej7;

public class MainEj7Runnable{

    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador(0);

        Hilo7Runnable a = new Hilo7Runnable(contador);
        Hilo7Runnable b = new Hilo7Runnable(contador);
        Hilo7Runnable c = new Hilo7Runnable(contador);
        Hilo7Runnable d = new Hilo7Runnable(contador);
        Hilo7Runnable e = new Hilo7Runnable(contador);

        a.run();
        b.run();
        c.run();
        d.run();
        e.run();

        System.out.println("Contador compartido: " + contador.get());
    }
}
