package ej5;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteEj1 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6051)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            do {
                System.out.println("Introduce una cadena (o * para salir):");
                mensaje = scanner.nextLine();
                out.println(mensaje);

                if (!mensaje.equals("*")) {
                    String respuesta = in.readLine();
                    System.out.println("Respuesta del servidor: " + respuesta);
                }
            } while (!mensaje.equals("*"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
