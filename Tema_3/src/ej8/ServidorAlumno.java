package ej8;

import java.io.*;
import java.net.*;

public class ServidorAlumno {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int puerto = 6008; // Puerto del servidor
        DatagramSocket socketServidor = new DatagramSocket(puerto);
        System.out.println("Servidor UDP iniciado. Esperando solicitudes...");

        //Inicializamos el arreglo de alumnos
        Curso curso1 = new Curso("C1", "Matemáticas");
        Curso curso2 = new Curso("C2", "Historia");
        Curso curso3 = new Curso("C3", "Biología");

        Alumno[] alumnos = {
                new Alumno("A1", "Juan", curso1, 8.5),
                new Alumno("A2", "María", curso2, 9.0),
                new Alumno("A3", "Pedro", curso1, 7.5),
                new Alumno("A4", "Ana", curso3, 8.0),
                new Alumno("A5", "Luis", curso2, 6.5),
        };

        boolean ejecutando = true;
        while (ejecutando) {
            //Recibimos la solicitud del cliente
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketServidor.receive(paqueteRecibido);

            //Deserializamos el idAlumno recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            String idAlumnoSolicitado = (String) ois.readObject();

            System.out.println("Solicitud recibida para el alumno con id: " + idAlumnoSolicitado);

            // Si se recibe "*", detener el servidor
            if (idAlumnoSolicitado.equalsIgnoreCase("*")) {
                System.out.println("Solicitud de cierre recibida. Cerrando servidor...");
                ejecutando = false;
                continue;
            }

            //Buscamos el alumno
            Alumno respuesta = null;
            for (Alumno alumno : alumnos) {
                if (alumno.getIdAlumno().equals(idAlumnoSolicitado)) {
                    respuesta = alumno;
                    break;
                }
            }

            //Serializamos y enviamos la respuesta
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            if (respuesta == null) {
                oos.writeObject("El alumno con id '" + idAlumnoSolicitado + "' no existe.");
            } else {
                oos.writeObject(respuesta);
            }
            oos.flush();

            byte[] bufferSalida = baos.toByteArray();
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length,
                    paqueteRecibido.getAddress(), paqueteRecibido.getPort());
            socketServidor.send(paqueteSalida);

            System.out.println("Respuesta enviada.");
        }
        //Cerramos el socket del servidor
        socketServidor.close();
        System.out.println("Servidor cerrado.");
    }
}
