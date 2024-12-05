package ej4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6004)) {
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Introducción del número y envío del mensaje
            System.out.println("Introduce un número entero:");
            int n = sc.nextInt();
            out.println(n);

            //Recibe el mensaje con el cuadrado
            String r = in.readLine();
            System.out.println("Cuadrado del número recibido: " + r);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
