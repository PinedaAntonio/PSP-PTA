package Ej4;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Servidor {
    public static void main(String[] args) throws Exception {
        int puerto = 6004;

        //Cargamos el almacén de claves
        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\Usuario\\Desktop\\2DAM\\PSP-PTA\\Tema_5\\Tema_5\\src\\Ej4\\AlmacenEj4.jks"); //Hay que poner la ruta absoluta o no encontrará el archivo
        System.setProperty("javax.net.ssl.keyStorePassword", "password");//Lo llamo password y no contraseña para evitar posibles problemas con la ñ

        //Creamos la fábrica de sockets SSL
        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket servidor = (SSLServerSocket) factory.createServerSocket(puerto);

        System.out.println("Servidor SSL esperando cliente...");
        SSLSocket socket = (SSLSocket) servidor.accept();

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream());

        //Enviamos un mensaje al cliente
        out.writeUTF("MENSAJE");

        //Recibimos el mensaje del servidor en minúsculas
        String r = in.readUTF();
        System.out.println("Cliente: " + r);

        socket.close();
        servidor.close();
    }
}