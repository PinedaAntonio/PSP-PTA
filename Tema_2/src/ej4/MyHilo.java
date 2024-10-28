package ej4;

import java.util.Scanner;

public class MyHilo extends Thread{
    public SolicitaSuspender suspender = new SolicitaSuspender();
    public int contador = 0;
    public boolean running = true;

    public void Suspende(){
        suspender.set(true);
    }

    public void Reanuda(){
        suspender.set(false);
    }

    public void detener(){
        running = false;
    }

    @Override
    public void run() {
        try{
            while(running) {
                contador++;
                mostrar();
                Thread.sleep(1000);
                suspender.esperandoParaReanudar();
            }
            System.out.println("Valor final del contador: " + contador);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public void mostrar(){
        System.out.println("Valor del contador:" + contador);
    }

}
