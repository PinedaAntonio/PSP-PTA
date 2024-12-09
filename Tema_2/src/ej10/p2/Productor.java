package ej10.p2;

public class Productor extends Thread {
    private Cola cola;

    public Productor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        String[] mensajes = {"PING", "PONG"};
        for (int i = 0; i < 10; i++) {
            String mensaje = mensajes[i % 2];
            cola.put(mensaje);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
