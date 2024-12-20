package ej8;

import java.io.*;
import java.net.*;

public class ClienteAlumno {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int puerto = 6008;

        //Iniciamos el cliente UDP
        System.out.println("Cliente UDP iniciado...");
        DatagramSocket socketCliente = new DatagramSocket();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Introduce el id del alumno que deseas consultar (escribe '*' para finalizar): ");
            String idAlumno = br.readLine();

            if (idAlumno.equalsIgnoreCase("*")) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject("*");
                oos.flush();

                byte[] bufferSalida = baos.toByteArray();
                InetAddress direccionServidor = InetAddress.getByName(host);
                DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puerto);
                socketCliente.send(paqueteSalida);

                System.out.println("Cliente finalizado.");
                socketCliente.close();
                break;
            }

            //Serializamos el idAlumno y se lo enviamos al servidor
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(idAlumno);
            out.flush();

            byte[] bufferSalida = bout.toByteArray();
            InetAddress direccionServidor = InetAddress.getByName(host);
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puerto);
            socketCliente.send(paqueteSalida);

            //Recibimos la respuesta del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketCliente.receive(paqueteEntrada);

            //Deserializamos la respuesta
            ByteArrayInputStream bin = new ByteArrayInputStream(paqueteEntrada.getData());
            ObjectInputStream oin = new ObjectInputStream(bin);
            Object respuesta = oin.readObject();

            //Mostramos la respuesta
            System.out.println("Respuesta del servidor: " + respuesta);
        }
    }
}
