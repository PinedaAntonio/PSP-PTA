package ej5;


import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        int puerto = 6005;
        String host = "localhost";

        Socket socket = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        //Recibimos el mensaje del servidor
        String m = in.readUTF();
        System.out.println("Mensaje recibido del servidor: " + m);

        //Enviamos la respuesta al servidor
        out.writeUTF("Mensaje recibido con éxito");

        socket.close();
    }
}


