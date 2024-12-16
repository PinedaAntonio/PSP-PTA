package ej7;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String Host = "localhost";
        int Puerto = 6007;

        //Iniciamos el cliente
        System.out.println("Cliente iniciado...");
        Socket cliente = new Socket(Host, Puerto);

        //Pedimos la entrada por teclado
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduce un número mayor que 0: ");
        int numero = Integer.parseInt(br.readLine());

        //Creamos un objeto Numeros
        Numeros objNum = new Numeros(numero);

        //Enviamos el objeto al servidor
        ObjectOutputStream salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.writeObject(objNum);

        if(objNum.getNumero() <= 0){
            System.out.println("Número menor o igual a 0 detectado, cerrar cliente y servidor");
        } else{
            //Recibimos el objeto del servidor
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            objNum = (Numeros) entrada.readObject();
            System.out.println("Número: " + objNum.getNumero());
            System.out.println("Cuadrado: " + objNum.getCuadrado());
            System.out.println("Cubo: " + objNum.getCubo());
            entrada.close();
        }


        //Cerramos recursos
        salida.close();
        cliente.close();
    }
}

