package ej3;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6003;
        ServerSocket servidor = new ServerSocket(puerto);
        Socket socket = null;
        System.out.println("Servidor esperando cliente");
        socket = servidor.accept();
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        // Enviamos mensaje al cliente
        out.writeUTF("MENSAJE");

        // Recibimos mensaje en minúsculas
        String r = in.readUTF();
        System.out.println("Cliente: " + r);

        //Cerramos conexiones
        socket.close();
        servidor.close();
    }
}
