package ej2;

//Esta clase crea el hilo que imprime tic

class Hilo1 implements Runnable{
    public void run() {
        System.out.println("Hola Mundo" + " Hilo_id=" + Thread.currentThread().getId());
    }
}
