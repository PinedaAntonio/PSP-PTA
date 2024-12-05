package ej1;

import java.net.*;

public class Ejemplo1 {
    public static void main(String[] args) {
        InetAddress dir = null;
        System.out.println("=================================");
        System.out.println("SALIDA PARA LOCALHOST");
        try {
            //LOCALHOST
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);

            //URL GOOGLE
            System.out.println("=================================");
            System.out.println("SALIDA PARA UNA URL");
            dir = InetAddress.getByName("www.google.es");
            pruebaMetodos(dir);

            System.out.println("\tDIRECCIONES IP PARA:  " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for(int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }
            System.out.println("=================================");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void pruebaMetodos(InetAddress dir){
        System.out.println("\tMetodo getByName():   " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost():    " + dir2);
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
        System.out.println("\tMetodo getHostName()  :" + dir.getHostName());
        System.out.println("\tMetodo getHostAdress()  :" + dir.getHostAddress());
        System.out.println("\tMetodo toString()  :" + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName()  :" + dir.getCanonicalHostName());
    }
}
