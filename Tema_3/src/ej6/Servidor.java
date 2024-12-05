package ej6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(6006); // Puerto del servidor
            byte[] buffer = new byte[1024];
            System.out.println("Servidor iniciado.");

            while (true) {
                // Recibir paquete
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);
                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());

                // Finaliza si recibe "*"
                if (mensaje.equals("*")) {
                    System.out.println("El cliente ha terminado la conexión.");
                    break;
                }

                // Mostrar mensaje recibido y enviarlo en mayúsculas
                System.out.println("Mensaje recibido: " + mensaje);
                String respuesta = mensaje.toUpperCase();

                // Enviar respuesta
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                byte[] respuestaBytes = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(respuestaBytes, respuestaBytes.length, direccionCliente, puertoCliente);
                socket.send(paqueteRespuesta);
            }

            socket.close();
            System.out.println("Servidor finalizado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

