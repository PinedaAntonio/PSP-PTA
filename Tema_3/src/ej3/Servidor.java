package ej3;
import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6003)) {
            System.out.println("Servidor activo");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("MENSAJE");
            String r = in.readLine();
            System.out.println("Mensaje del cliente: " + r);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
