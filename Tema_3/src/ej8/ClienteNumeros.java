package ej8;

import java.io.*;
import java.net.*;

public class ClienteNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6037;

        //Iniciamos el cliente UDP
        System.out.println("Cliente UDP iniciado...");

        //Creamos un socket UDP
        DatagramSocket socketCliente = new DatagramSocket();

        //Pedimos la entrada por teclado
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduce un número mayor que 0: ");
        int numero = Integer.parseInt(br.readLine());

        //Creamos un objeto Numeros
        Numeros objNum = new Numeros(numero);

        //Serializamos el objeto y lo enviamos al servidor
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(objNum);
        oos.flush();

        byte[] bufferSalida = baos.toByteArray();
        InetAddress direccionServidor = InetAddress.getByName(Host);
        DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, Puerto);
        socketCliente.send(paqueteSalida); //Enviamos el paquete

        if (numero > 0) {
            //Recibimos la respuesta del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socketCliente.receive(paqueteEntrada);

            //Deserializamos el objeto recibido
            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteEntrada.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            objNum = (Numeros) ois.readObject();


            //Mostramos los resultados
            System.out.println("Número: " + objNum.getNumero());
            System.out.println("Cuadrado: " + objNum.getCuadrado());
            System.out.println("Cubo: " + objNum.getCubo());

        }

        //Cerramos recursos
        socketCliente.close();
        System.out.println("Cliente finalizado.");
    }
}
