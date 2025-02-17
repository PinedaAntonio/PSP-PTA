package ej2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        int puerto = 6000;
        String host = "localhost";

        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        System.out.println("PROGRAMA CLIENTE INICIADO...");

        //Recibimos el mensaje del servidor
        String m = in.readUTF();
        System.out.println(m);

        String m2 = "0";
        while(true) {
            if(!m2.equals("*")) {
                System.out.println("Introduce identificador a consultar: ");
                m2 = sc.next();
                int n = Integer.parseInt(m2);
                out.writeInt(n);

                m = in.readUTF();
                System.out.println(m);
            }
            else{
                out.writeInt(0);
                break;
            }
        }

        socket.close();
    }
}


