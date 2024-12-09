package ej6;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        //Dirección del servidor
        InetAddress direccion = InetAddress.getLocalHost();
        int puerto = 6006;
        Scanner sc = new Scanner(System.in);

        System.out.println("Cliente iniciado.");
        System.out.println("Introduce texto para enviar al servidor (introduce '*' para salir):");

        while (true) {
            //Leemos la entrada del usuario
            System.out.print("Mensaje: ");
            String m = sc.nextLine();

            //Enviamos el mensaje al servidor
            byte[] envio = m.getBytes();
            DatagramPacket e = new DatagramPacket(envio, envio.length, direccion, puerto);
            socket.send(e);

            //Cerramos conexión si el mensaje recibido es un "*"
            if (m.equals("*")) {
                System.out.println("Conexión cerrada por el cliente.");
                break;
            }

            //Recibimos respuesta del servidor con timeout
            byte[] respuesta = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta, respuesta.length);
            socket.setSoTimeout(4000); //Timeout de 4 segundos en milisegundos

            try {
                socket.receive(paqueteRespuesta);
                String r = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                System.out.println("Respuesta del servidor: " + r);
            } catch (java.net.SocketTimeoutException ex) {
                System.out.println("Tiempo de espera agotado. Paquete perdido.");
            }
        }

        socket.close();
    }
}

