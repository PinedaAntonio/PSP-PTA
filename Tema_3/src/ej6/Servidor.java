package ej6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int puerto = 6006;
        //Puerto del servidor
        DatagramSocket socket = new DatagramSocket(puerto);
        byte[] buffer = new byte[1024];
        System.out.println("Servidor iniciado.");

        while (true) {
            //Recibimos el paquete
            DatagramPacket recibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(recibido);
            String mensaje = new String(recibido.getData(), 0, recibido.getLength());

            //Finalizamos si el mensaje es "*"
            if (mensaje.equals("*")) {
                System.out.println("El cliente ha cerrado la conexión.");
                break;
            }

            //Mostramos el mensaje recibido y lo enviamos en mayúsculas
            System.out.println("Mensaje recibido: " + mensaje);
            String respuesta = mensaje.toUpperCase();

            //Enviamos la respuesta
            InetAddress direccion = recibido.getAddress();
            int puertoCliente = recibido.getPort();
            byte[] r = respuesta.getBytes();
            DatagramPacket paqueteRespuesta = new DatagramPacket(r, r.length, direccion, puertoCliente);
            socket.send(paqueteRespuesta);
        }

        socket.close();
        System.out.println("Servidor finalizado.");

    }
}

