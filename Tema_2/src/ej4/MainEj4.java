package ej4;

import java.util.Scanner;

public class MainEj4 {

    public static void main(String[] args) {
        String cadena = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce caracteres (* para detener, s para suspender, r para reanudar): ");

        MyHilo hilo = new MyHilo();
        hilo.start();

        while(true){

            cadena = sc.nextLine();
            if(cadena.equalsIgnoreCase("*")){
                hilo.detener();
                System.out.println("Proceso finalizado");
            }
            if(cadena.equalsIgnoreCase("s")){
                hilo.Suspende();
            }
            if(cadena.equalsIgnoreCase("r")){
                hilo.Reanuda();
            }
        }

    }
}
