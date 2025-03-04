package Ej4;

import javax.net.ssl.*;
import java.io.*;

//Para generar, guardar y usar las claves hay que hacer tres comandos:
//1.Generar: keytool -genkey -alias servidor -keyalg RSA -keystore AlmacenEj4.jks -storepass password
//2.Exportar certificado al servidor: keytool -export -alias servidor -keystore AlmacenEj4.jks -file server.cer -storepass password
//3.Importar el certificado en el truststore del cliente: keytool -import -alias servidor -file server.cer -keystore AlmacenConfianzaEj4.jks -storepass password -noprompt
public class Cliente {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 6004;

        //Configuramos el almacén de confianza del cliente
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Usuario\\Desktop\\2DAM\\PSP-PTA\\Tema_5\\Tema_5\\src\\Ej4\\AlmacenConfianzaEj4.jks"); //Hay que poner la ruta absoluta o no encontrará el archivo
        System.setProperty("javax.net.ssl.trustStorePassword", "password"); //Lo llamo password y no contraseña para evitar posibles problemas con la ñ

        //Creamos la fábrica de sockets SSL
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket cliente = (SSLSocket) factory.createSocket(host, port);

        DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
        DataInputStream in = new DataInputStream(cliente.getInputStream());

        //Recibimos un mensaje del servidor
        String m = in.readUTF();
        System.out.println("Mensaje recibido: " + m);

        //Enviamos el mensaje en minúsculas al cliente
        out.writeUTF(m.toLowerCase());

        cliente.close();
    }
}