package ej1;

//Aquí se ejecutan 5 hilos de la clase HiloEj1

public class Ej1Main {
    public static void main(String[] args) {
        HiloEj1 hilo1 = new HiloEj1();
        HiloEj1 hilo2 = new HiloEj1();
        HiloEj1 hilo3 = new HiloEj1();
        HiloEj1 hilo4 = new HiloEj1();
        HiloEj1 hilo5 = new HiloEj1();

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
