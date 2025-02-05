package Ej4;

import java.util.Scanner;
import org.apache.commons.net.smtp.*;

public class Ej4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce servidor SMTP (localhost para Mercury): ");
        String server = scanner.nextLine();

        System.out.print("Introduce puerto (25 para Mercury): ");
        int port = Integer.parseInt(scanner.nextLine());

        //Solicitamos las credenciales del usuario para autenticarse en el servidor SMTP
        System.out.print("Introduce usuario: ");
        String username = scanner.nextLine();

        System.out.print("Introduce password: ");
        String password = scanner.nextLine();

        System.out.print("Introduce correo del remitente: ");
        String remitente = scanner.nextLine();

        System.out.print("Introduce correo destinatario: ");
        String destinatario = scanner.nextLine();

        System.out.print("Introduce asunto: ");
        String asunto = scanner.nextLine();

        System.out.println("Introduce el mensaje, finalízalo con un *: ");
        StringBuilder mensaje = new StringBuilder();
        while (true) {
            String linea = scanner.nextLine();
            if (linea.equals("*")) break;
            mensaje.append(linea).append("\n");
        }

        scanner.close();

        try {
            //Establecemos una conexión con el servidor SMTP
            AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
            client.connect(server, port);
            System.out.println("1 - " + client.getReplyString());

            //Verificamos si la conexión fue exitosa
            int respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }

            //Realizamos el saludo 'EHLO' al servidor SMTP
            client.ehlo(server);
            System.out.println("2 - " + client.getReplyString());

            //Realizamos la autenticación con el servidor SMTP usando las credenciales proporcionadas
            if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
                System.out.println("3 - " + client.getReplyString());
            }

            //Creamos la cabecera del mensaje con la dirección del remitente, destinatario y asunto
            SimpleSMTPHeader header = new SimpleSMTPHeader(remitente, destinatario, asunto);
            client.setSender(remitente);
            client.addRecipient(destinatario);
            System.out.println("4 - " + client.getReplyString());

            //Envía el contenido del mensaje
            client.sendShortMessageData(mensaje.toString());
            System.out.println("5 - " + client.getReplyString());

            //Cierra la sesión y desconecta
            client.logout();
            client.disconnect();
            System.out.println("Correo enviado con éxito usando Mercury Mail en XAMPP.");
        } catch (Exception e) {
            //Captura y muestra cualquier error que ocurra durante el envío del correo
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}