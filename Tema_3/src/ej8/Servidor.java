package ej8;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Configurar el servidor
        int puerto = 6008;
        DatagramSocket socketServidor = new DatagramSocket(puerto);
        System.out.println("Servidor UDP iniciado. Esperando datagramas...");

        while (true) {
            // Recibir el datagrama
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketServidor.receive(paqueteRecibido);

            // Convertir los bytes a objeto Persona
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Persona persona = (Persona) ois.readObject();

            // Mostrar la información recibida
            System.out.println("Objeto recibido: " + persona);

            // Modificar el objeto
            persona.setNombre(persona.getNombre() + " Modificado");
            persona.setEdad(persona.getEdad() + 1);

            // Enviar el objeto modificado al cliente
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.flush();

            byte[] bufferSalida = baos.toByteArray();
            DatagramPacket paqueteSalida = new DatagramPacket(
                    bufferSalida, bufferSalida.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
            socketServidor.send(paqueteSalida);

            System.out.println("Objeto modificado enviado: " + persona);
        }
    }
}

