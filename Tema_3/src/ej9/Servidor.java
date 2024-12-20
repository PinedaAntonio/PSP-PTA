package ej9;

import java.io.*;
import java.net.*;

public class Servidor {
    private static boolean ejecutando = true;

    public static void main(String[] args) {
        int puerto = 44444;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado...");

            while (ejecutando) {
                Socket cliente = servidor.accept();
                System.out.println("----- IP conectada: " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());

                //Creamos un hilo para poder manejar al cliente
                new HiloCliente(cliente, servidor).start();
            }

            System.out.println("Servidor finalizado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método para detener el servidor
    public static void detenerServidor() {
        ejecutando = false;
    }
}

class HiloCliente extends Thread {
    private Socket cliente;
    private ServerSocket servidor;

    public HiloCliente(Socket cliente, ServerSocket servidor) {
        this.cliente = cliente;
        this.servidor = servidor;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true)) {

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    System.out.println("----- IP desconectada: " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
                    Servidor.detenerServidor(); //Detener el servidor si recibe un "*"
                    break;
                }
                //Enviamos el mensaje en mayúsculas al cliente
                salida.println(mensaje.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
