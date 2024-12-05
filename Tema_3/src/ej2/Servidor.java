package ej2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int Puerto = 6002;

        ServerSocket Servidor = new ServerSocket(Puerto);
        System.out.println("Servidor escuchando en el puerto " + Servidor.getLocalPort());

        for (int clienteNum = 1; clienteNum <= 2; clienteNum++) {
            System.out.println("Esperando al cliente " + clienteNum + "...");

            Socket Cliente = Servidor.accept();

            System.out.println("Cliente " + clienteNum + " conectado:");
            System.out.println("\tPuerto local: " + Cliente.getLocalPort());
            System.out.println("\tPuerto remoto: " + Cliente.getPort());

            Cliente.close();
        }

        System.out.println("Dos clientes conectados, cerrando servidor");
        Servidor.close();
    }
}
