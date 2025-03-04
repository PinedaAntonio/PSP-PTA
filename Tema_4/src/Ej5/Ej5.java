package Ej5;

import java.io.IOException;
<<<<<<< HEAD
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ej5 {
    public static void main(String[] args) {
        String server = "pop.gmail.com", username = "traditionalland@gmail.com", password = "yyjl ozmr ycxz rkgr";
        int puerto = 995;

        POP3SClient pop3 = new POP3SClient(true);
        try {
            pop3.connect(server, puerto);
            System.out.println("Conexion realizada con el servidor");

            if (!pop3.login(username, password)) {
                System.out.println("Error al hacer login");
            } else {
                POP3MessageInfo[] men = pop3.listMessages();

                if (men == null)
                    System.out.println("No se puede listar los mensajes");
                else {
                    System.out.println("num mensajes: " + men.length);
                    recuperarMensajes(men, pop3);
                }
                pop3.logout();
            }
            pop3.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
=======
import org.apache.commons.net.pop3.*;

public class ClientePOP3 {
    public static void main(String[] args) {
        String server = "pop.gmail.com", username = "antoniopinedatoro@gmail.com", password = "swhq mkek ihce viuc";
        int puerto = 995;

        POP3SClient clientePOP3 = new POP3SClient(true);
        try {
            clientePOP3.connect(server, puerto);
            System.out.println("Conexión establecida con éxito");

            if (!clientePOP3.login(username, password)) {
                System.out.println("Error en el inicio de sesión");
            } else {
                POP3MessageInfo[] mensajes = clientePOP3.listMessages();

                if (mensajes == null)
                    System.out.println("No se pueden listar los mensajes");
                else {
                    System.out.println("Total de mensajes: " + mensajes.length);
                    mostrarMensajes(mensajes, clientePOP3);
                }
                clientePOP3.logout();
            }
            clientePOP3.disconnect();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
>>>>>>> 966aefcd07b422b9a3d41c57d39ee71f0a4741d2
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

<<<<<<< HEAD
    private static void recuperarMensajes(POP3MessageInfo[] mensajes, POP3SClient pop3) throws IOException {
=======
    private static void mostrarMensajes(POP3MessageInfo[] mensajes, POP3SClient clientePOP3) throws IOException {
>>>>>>> 966aefcd07b422b9a3d41c57d39ee71f0a4741d2
        for (int i = 0; i < mensajes.length; i++) {
            System.out.println("\nMensaje " + (i + 1));
            POP3MessageInfo msgInfo = mensajes[i];

            System.out.println("ID: " + msgInfo.identifier);
            System.out.println("Número: " + msgInfo.number);
            System.out.println("Tamaño: " + msgInfo.size + " bytes");

<<<<<<< HEAD
            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
=======
            POP3MessageInfo pmi = clientePOP3.listUniqueIdentifier(i + 1);
>>>>>>> 966aefcd07b422b9a3d41c57d39ee71f0a4741d2
            if (pmi != null) {
                System.out.println("Identificador único: " + pmi.identifier);
            } else {
                System.out.println("No se pudo obtener el identificador único.");
            }
        }
    }
}
