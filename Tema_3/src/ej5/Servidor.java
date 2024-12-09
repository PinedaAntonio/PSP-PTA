package ej5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int n;
        int puerto = 6005;
        ServerSocket serverSocket = new ServerSocket(puerto);

        System.out.println("Escriba el máximo de clientes");
        n = sc.nextInt();

        int cliente_actual = 1;

        while (cliente_actual <= n) {
            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            //Enviamos el número de cliente
            out.writeUTF("Número de cliente: " + cliente_actual);

            //Recibimos el mensaje del cliente
            String m = in.readUTF();
            System.out.println("Mensaje del cliente " + cliente_actual + ": " + m);

            socket.close();
            cliente_actual++;
        }

        serverSocket.close();
    }
}

