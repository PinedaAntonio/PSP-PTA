package ej1;

//Esta clase ejecuta los dos hilos.
//Salen de manera ordenada debido al sleep de cada proceso,
//si tuvieran sleeps con distinto tiempo o no hubiera sleep, no saldrían siempre ordenados

public class Main {

    public static void main(String[] args) {

        Hilo1 hilo1 = new Hilo1();
        Hilo2 hilo2 = new Hilo2();

        hilo1.start();
        hilo2.start();

    }
}
