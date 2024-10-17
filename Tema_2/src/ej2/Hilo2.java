package ej2;

//Esta clase crea el hilo que imprime tac

class Hilo2 extends Thread {
    public void run() {
        int i = 1;
        while (i > 0) {
            System.out.println("TAC");
            try {
                Thread.sleep(500); //Sleep de medio segundo para poder leer la ejecución
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
