package Ej5;

import java.io.IOException;
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
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void mostrarMensajes(POP3MessageInfo[] mensajes, POP3SClient clientePOP3) throws IOException {
        for (int i = 0; i < mensajes.length; i++) {
            System.out.println("\nMensaje " + (i + 1));
            POP3MessageInfo msgInfo = mensajes[i];

            System.out.println("ID: " + msgInfo.identifier);
            System.out.println("Número: " + msgInfo.number);
            System.out.println("Tamaño: " + msgInfo.size + " bytes");

            POP3MessageInfo pmi = clientePOP3.listUniqueIdentifier(i + 1);
            if (pmi != null) {
                System.out.println("Identificador único: " + pmi.identifier);
            } else {
                System.out.println("No se pudo obtener el identificador único.");
            }
        }
    }
}
