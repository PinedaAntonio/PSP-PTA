package ej8;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Configurar el cliente
        String host = "localhost";
        int puerto = 6008;
        DatagramSocket socketCliente = new DatagramSocket();

        // Crear objeto Persona
        Persona persona = new Persona("Juan", 25);
        System.out.println("Objeto a enviar: " + persona);

        // Serializar el objeto y enviarlo al servidor
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(persona);
        oos.flush();

        byte[] bufferSalida = baos.toByteArray();
        DatagramPacket paqueteSalida = new DatagramPacket(
                bufferSalida, bufferSalida.length, InetAddress.getByName(host), puerto);
        socketCliente.send(paqueteSalida);

        // Recibir la respuesta del servidor
        byte[] bufferEntrada = new byte[1024];
        DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socketCliente.receive(paqueteRecibido);

        // Convertir los bytes recibidos a objeto Persona
        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Persona personaModificada = (Persona) ois.readObject();

        // Mostrar el objeto recibido
        System.out.println("Objeto recibido del servidor: " + personaModificada);

        // Cerrar el socket
        socketCliente.close();
    }
}
