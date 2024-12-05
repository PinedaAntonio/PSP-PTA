package ej4;
import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6004)) {
            System.out.println("Servidor listo...");
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int n = Integer.parseInt(in.readLine());
            int nCuadrado = n * n;
            out.println(nCuadrado);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
