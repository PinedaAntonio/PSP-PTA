package ej7;

public class MainEj7Thread extends Thread{
    public static int contador = 0;

    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador++;
        }
    }

    public static void main(String[] args) {
        MainEj7Thread a = new MainEj7Thread();
        MainEj7Thread b = new MainEj7Thread();
        MainEj7Thread c = new MainEj7Thread();
        MainEj7Thread d = new MainEj7Thread();
        MainEj7Thread e = new MainEj7Thread();

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

        System.out.println("Contador compartido: " + contador);
    }
}
