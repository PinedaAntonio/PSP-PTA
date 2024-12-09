package ej5;

import java.io.*;
import java.net.*;

public class ServidorEj1 {
    public static void main(String[] args) throws IOException {
        int puerto = 6051;
        ServerSocket serverSocket = new ServerSocket(puerto);
        System.out.println("Servidor iniciado, esperando...");
        Socket socket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        while (true) {
            //Recibimos el mensaje del cliente
            String m = in.readUTF();
            if (m.equals("*")) {
                System.out.println("Conexión cerrada por el cliente");
                break;
            }

            //Calculamos el número de caracteres y los enviamos al cliente
            int nCaracteres = m.length();
            out.writeInt(nCaracteres);
            System.out.println("Mensaje recibido: " + m);
            System.out.println("Caracteres recibidos: " + nCaracteres);
        }

        socket.close();
        serverSocket.close();
    }
}