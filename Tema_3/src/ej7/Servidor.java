package ej7;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int Puerto = 6007;
        boolean continuar = true;

        //Iniciamos el servidor
        System.out.println("Servidor iniciado, esperando cliente...");
        ServerSocket servidor = new ServerSocket(Puerto);

        while (continuar) {
            Socket cliente = servidor.accept();
            System.out.println("Conexión establecida con el cliente");

            //Recibimos el objeto del cliente
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            Numeros objNum = (Numeros) entrada.readObject();

            if (objNum.getNumero() <= 0) {
                System.out.println("Número menor o igual a 0 recibido. Cerrando servidor...");
                continuar = false;
            } else {
                //Calculamos el cuadrado y el cubo del número
                objNum.setNumero(objNum.getNumero());

                //Enviamos el objeto modificado al cliente
                ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
                salida.writeObject(objNum);

                //Cerramos recursos
                salida.close();
                System.out.println("Número procesado y enviado al cliente.");
            }

            entrada.close();
            cliente.close();
        }


        //Cerramos recursos
        servidor.close();
        System.out.println("Servidor cerrado.");
    }
}
