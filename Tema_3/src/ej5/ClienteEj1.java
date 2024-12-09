package ej5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEj1 {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int puerto = 6051;
        Socket socket = new Socket(host, puerto);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce un mensaje (o '*' para salir):");
            String mensaje = sc.nextLine();

            //Enviamos el mensaje al servidor
            out.writeUTF(mensaje);

            if (mensaje.equals("*")) {
                System.out.println("Conexión finalizada.");
                break;
            }

            //Recibimos el número de caracteres del servidor
            int nCaracteres = in.readInt();
            System.out.println("El mensaje tiene " + nCaracteres + " caracteres.");
        }

        socket.close();
    }
}
