package ej2;

//Esta clase ejecuta los dos hilos.
//Salen de manera ordenada debido al sleep de cada proceso,
//si tuvieran sleeps con distinto tiempo o no hubiera sleep, no saldrían siempre ordenados

public class Main {

    public static void main(String[] args) {

        new Thread(new Hilo1()).start();
        new Thread(new Hilo1()).start();
        new Thread(new Hilo1()).start();
        new Thread(new Hilo1()).start();
        new Thread(new Hilo1()).start();

    }
}
