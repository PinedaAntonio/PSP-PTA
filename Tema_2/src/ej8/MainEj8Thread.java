package ej8;

public class MainEj8Thread extends Thread {
    private Saldo saldo;

    public MainEj8Thread(Saldo saldo) {
        this.saldo = saldo;
    }

    @Override
    public void run() {
        try {
            int r1 = (int) (Math.random() * 100);
            int r2 = (int) (Math.random() * 100);
            int r3 = (int) (Math.random() * 100);

            saldo.incrementa(r1, Thread.currentThread());
            saldo.incrementa(r2, Thread.currentThread());
            saldo.incrementa(r3, Thread.currentThread());

            System.out.println(Thread.currentThread().getName() + ": Saldo final = " + saldo.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Saldo saldo = new Saldo(0);
        MainEj8Thread a = new MainEj8Thread(saldo);
        MainEj8Thread b = new MainEj8Thread(saldo);
        MainEj8Thread c = new MainEj8Thread(saldo);

        a.start();
        b.start();
        c.start();
    }
}
