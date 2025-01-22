package Ej1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Ej1Local {
    public static void main(String[] args) {
        // Datos de conexión al servidor FTP
        String servidor = "localhost"; // Dirección del servidor
        int puerto = 21; // Puerto estándar de FTP
        String usuario = "Usuario1"; // Usuario configurado en el servidor
        String contraseña = "usu1"; // Contraseña del usuario

        FTPClient clienteFTP = new FTPClient();

        try {
            // Conexión al servidor
            clienteFTP.connect(servidor, puerto);
            if (!clienteFTP.login(usuario, contraseña)) {
                System.out.println("Error: Fallo en la autenticación.");
                return;
            }

            System.out.println("Conexión exitosa al servidor FTP.");

            String ruta = "/";

            // Listar el contenido
            System.out.println("Contenido del escritorio:");
            var contenido = clienteFTP.listFiles(ruta);
            if (contenido != null && contenido.length > 0) {
                for (var elemento : contenido) {
                    System.out.println(elemento.getName());
                }
            } else {
                System.out.println("El directorio raíz está vacío o no es accesible.");
            }

            // Finalizar sesión
            clienteFTP.logout();
            System.out.println("Sesión finalizada correctamente.");
        } catch (IOException e) {
            System.out.println("Error durante la operación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (clienteFTP.isConnected()) {
                    clienteFTP.disconnect();
                    System.out.println("Desconexión realizada con éxito.");
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
