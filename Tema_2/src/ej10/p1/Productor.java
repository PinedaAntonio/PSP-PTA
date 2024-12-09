package ej10.p1;

public class Productor extends Thread {
    private Cola cola;
    private int n;
    public Productor(Cola cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println(i + " => Productor: " + n + ", Produce: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin productor...");
    }
}
