package ej1;

import java.net.*;
import java.util.Scanner;

public class Ej1Main {
    public static void main(String[] args) {
        InetAddress dir = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================================");
        System.out.println("Ingrese un nombre de máquina o una dirección IP:");
        String input = scanner.nextLine();

        try {

            dir = InetAddress.getByName(input);
            System.out.println("==================================================================");
            System.out.println("SALIDA PARA LA DIRECCIÓN INGRESADA");
            pruebaMetodos(dir);

            System.out.println("\tDIRECCIONES IP PARA:  " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }
            System.out.println("==================================================================");
        } catch (Exception e) {
            System.out.println("Se produjo un error al procesar la entrada:");
            e.printStackTrace();
        }

        scanner.close();
    }

    public static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName():   " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost():    " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("\tMetodo getHostName():  " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress():  " + dir.getHostAddress());
        System.out.println("\tMetodo toString():  " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName():  " + dir.getCanonicalHostName());
    }
}

