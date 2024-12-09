package ej3;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 6003;
        Socket cliente = new Socket(host, port);
        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        DataInputStream in = new DataInputStream(cliente.getInputStream());

        // Recibimos mensaje del servidor
        String m = in.readUTF();
        System.out.println("Mensaje recibido: " + m);

        // Enviamos mensaje en minúsculas
        out.writeUTF(m.toLowerCase());

        cliente.close();
    }
}
