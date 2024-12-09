package ej10.p2;

public class Consumidor extends Thread {
    private Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String mensaje = cola.get();
            System.out.println(mensaje);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
