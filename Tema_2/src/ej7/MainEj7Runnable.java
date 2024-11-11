package ej7;

public class MainEj7Runnable implements Runnable {
    public static int contador = 0;

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            synchronized (MainEj7Runnable.class) {
                contador++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new MainEj7Runnable());
        Thread b = new Thread(new MainEj7Runnable());
        Thread c = new Thread(new MainEj7Runnable());
        Thread d = new Thread(new MainEj7Runnable());
        Thread e = new Thread(new MainEj7Runnable());

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

        a.join();
        b.join();
        c.join();
        d.join();
        e.join();

        System.out.println("Contador compartido: " + contador);
    }
}
