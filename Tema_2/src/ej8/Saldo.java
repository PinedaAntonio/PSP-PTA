package ej8;

public class Saldo {
    private int c = 0;

    Saldo(int c) throws InterruptedException {
        this.c = c;
        int random = (int)(Math.random()*10000);
        Thread.sleep(random);
    }

    public synchronized void incrementa(int add, Thread hilo) {
        System.out.println(hilo.getName() + ": Insertando (" + add + ") euros. Saldo anterior: " + c);
        c = c + add;
        System.out.println(hilo.getName() + ": Saldo actual: " + c);
    }

    public synchronized int get() throws InterruptedException {
        int random = (int)(Math.random()*10000);
        Thread.sleep(random);
        return c;
    }
}
