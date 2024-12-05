package ej6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionServidor = InetAddress.getByName("localhost"); // Dirección del servidor
            int puertoServidor = 6006;
            Scanner scanner = new Scanner(System.in);

            System.out.println("Cliente iniciado. Introduce texto para enviar al servidor (para salir, escribe '*'):");

            while (true) {
                // Leer entrada del usuario
                System.out.print("Mensaje: ");
                String mensaje = scanner.nextLine();

                // Envío del mensaje al servidor
                byte[] bufferEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, puertoServidor);
                socket.send(paqueteEnvio);

                // Finalizar si el mensaje es "*"
                if (mensaje.equals("*")) {
                    System.out.println("Conexión finalizada por el cliente.");
                    break;
                }

                // Recibir respuesta del servidor con timeout
                byte[] bufferRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
                socket.setSoTimeout(4000); // 4000 ms = 4 segundos

                try {
                    socket.receive(paqueteRespuesta);
                    String respuesta = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (java.net.SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. Paquete perdido.");
                }
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

