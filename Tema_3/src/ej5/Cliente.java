package ej5;


import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6005)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String mensaje = in.readLine();
            System.out.println("Mensaje recibido del servidor: " + mensaje);
            out.println("¡Mensaje recibido correctamente!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


