package ej4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        int puerto = 6004;
        String host = "localhost";
        Socket cliente = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        DataInputStream in = new DataInputStream(cliente.getInputStream());
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un número entero:");
        int n = sc.nextInt();

        //Enviamos el número al servidor
        out.writeInt(n);

        //Recibimos el cuadrado del número
        int r = in.readInt();
        System.out.println("Cuadrado del número: " + r);

        cliente.close();
    }
}
