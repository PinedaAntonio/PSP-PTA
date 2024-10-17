package ej2;

public class HiloEj2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Hola Mundo" + " Hilo_id=" + Thread.currentThread().getId());
    }
}
