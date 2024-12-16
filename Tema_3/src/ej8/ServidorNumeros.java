package ej8;

import java.io.*;
import java.net.*;

public class ServidorNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int Puerto = 6037; // Puerto del servidor
        DatagramSocket socketServidor = new DatagramSocket(Puerto);
        System.out.println("Servidor UDP iniciado. Esperando datagramas...");

        boolean ejecutar = true; //Esto es una variable para controlar la ejecución del servidor

        while (ejecutar) {
            //Recibimos el datagrama del cliente
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketServidor.receive(paqueteRecibido);

            //Deserializamos el objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros objNum = (Numeros) ois.readObject();

            System.out.println("Número recibido del cliente: " + objNum.getNumero());

            //Verificamos si el número es menor o igual a 0
            if (objNum.getNumero() <= 0) {
                System.out.println("Número menor o igual a 0 detectado. Cerrando servidor...");
                ejecutar = false; //Indicamos el cierre del servidor
            } else {
                //Calculamos cuadrado y cubo
                int numero = objNum.getNumero();
                objNum.setCuadrado((long) numero * numero);
                objNum.setCubo((long) numero * numero * numero);

                //Serializamos y enviamos el objeto modificado
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(objNum);
                oos.flush();

                byte[] bufferSalida = baos.toByteArray();
                DatagramPacket paqueteSalida = new DatagramPacket(
                        bufferSalida, bufferSalida.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socketServidor.send(paqueteSalida);

                System.out.println("Resultado enviado al cliente: Cuadrado = " + objNum.getCuadrado() + ", Cubo = " + objNum.getCubo());
            }
        }

        //Cerramos el socket
        socketServidor.close();
        System.out.println("Servidor finalizado.");
    }
}
