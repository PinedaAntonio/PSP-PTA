package ej5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N;
        System.out.println("Escribe maximo de clientes");
        N = sc.nextInt();

        int clienteActual = 1;

        try (ServerSocket serverSocket = new ServerSocket(6005)) {
            System.out.println("Servidor listo para atender clientes...");

            while (clienteActual <= N) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente " + clienteActual + " conectado.");

                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                out.println("Eres el cliente número: " + clienteActual);
                String mensaje = in.readLine();
                System.out.println("Cliente " + clienteActual + " dice: " + mensaje);

                socket.close();
                clienteActual++;
            }

            System.out.println("Número máximo de clientes alcanzado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

