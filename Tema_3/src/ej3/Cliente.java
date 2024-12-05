package ej3;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6003)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String m = in.readLine();
            System.out.println("Mensaje recibido: " + m);
            out.println(m.toLowerCase());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
