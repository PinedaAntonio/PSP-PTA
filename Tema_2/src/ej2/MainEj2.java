package ej2;

public class MainEj2 {
    public static void main(String[] args) {
        new Thread(new HiloEj2("Hilo 1")).start();
        new Thread(new HiloEj2("Hilo 2")).start();
        new Thread(new HiloEj2("Hilo 3")).start();
        new Thread(new HiloEj2("Hilo 4")).start();
        new Thread(new HiloEj2("Hilo 5")).start();
    }
}
