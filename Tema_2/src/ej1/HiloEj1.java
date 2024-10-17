package ej1;

//Esta clase muestra por pantalla el mensaje "Hola Mundo" más el id del proceso correspondiente

public class HiloEj1 extends Thread{
    @Override
    public void run() {
        System.out.println("Hola Mundo" + " Hilo_id=" + Thread.currentThread().getId());
    }

}
