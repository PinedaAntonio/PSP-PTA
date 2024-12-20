package ej10;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6010;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto: " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket socket;
    private Random random = new Random();
    private int numeroAdivinar = random.nextInt(25) + 1;
    private int intentos = 0;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer = new PrintWriter(output, true)
        ) {
            writer.println("¡Adivina un número entre 1 y 25!");

            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                try {
                    int numero = Integer.parseInt(mensaje);
                    intentos++;

                    if (numero == numeroAdivinar) {
                        writer.println("¡Correcto! Has adivinado el número en " + intentos + " intentos.");
                        break;
                    } else if (numero < numeroAdivinar) {
                        writer.println("El número es mayor.");
                    } else {
                        writer.println("El número es menor.");
                    }
                } catch (NumberFormatException e) {
                    writer.println("Ingresa un número válido.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

