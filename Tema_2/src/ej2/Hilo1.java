package ej2;

//Esta clase crea el hilo que imprime tic

class Hilo1 implements Runnable{
    public void run() {
        int i = 1;
        while (i > 0) {
            System.out.println("TIC");
            try {
                Thread.sleep(500); //Sleep de medio segundo para poder leer la ejecución
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
