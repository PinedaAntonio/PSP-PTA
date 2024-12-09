package ej4;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6004;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor iniciado, esperando cliente...");
        Socket socket = servidor.accept();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        //Recibimos el número del cliente
        int n = in.readInt();

        //Enviamos el cuadrado del número
        out.writeInt(n * n);

        //Cerramos conexiones
        socket.close();
        servidor.close();
    }
}
