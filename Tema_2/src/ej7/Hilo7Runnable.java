package ej7;

public class Hilo7Runnable implements Runnable {

    Contador contador;

    public Hilo7Runnable(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            synchronized (contador) {
                contador.incrementa();
            }
        }
    }
}
