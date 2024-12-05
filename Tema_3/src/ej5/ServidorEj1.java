package ej5;


import java.io.*;
import java.net.*;

public class ServidorEj1 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6051)) {
            System.out.println("Servidor esperando conexiones...");

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            while (!(mensaje = in.readLine()).equals("*")) {
                System.out.println("Mensaje recibido: " + mensaje);
                out.println("Número de caracteres: " + mensaje.length());
            }

            System.out.println("Se recibió un asterisco. Finalizando servidor.");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}