package ej10.p2;

public class Cola {
    private String mensaje;
    private boolean disponible = false;

    public synchronized String get() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;
        notifyAll();
        return mensaje;
    }

    public synchronized void put(String valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mensaje = valor;
        disponible = true;
        notifyAll();
    }
}
