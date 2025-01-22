package Ej1;

import org.apache.commons.net.ftp.*;
import java.io.IOException;

public class Ej1Remoto {

    public static void main(String[] args) {
        // Información de conexión al servidor FTP
        String servidorFTP = "ftp.rediris.es"; // Dirección del servidor FTP
        int puertoFTP = 21; // Puerto estándar para FTP
        String nombreUsuario = "anonymous"; // Usuario predeterminado para acceso anónimo
        String claveAcceso = ""; // Contraseña vacía para usuarios anónimos

        FTPClient clienteFTP = new FTPClient();

        try {
            // Establecer conexión con el servidor
            clienteFTP.connect(servidorFTP, puertoFTP);
            int codigoRespuesta = clienteFTP.getReplyCode();

            if (!clienteFTP.login(nombreUsuario, claveAcceso)) {
                System.out.println("Error: Fallo en la autenticación.");
                return;
            }

            System.out.println("Conexión exitosa al servidor FTP.");

            // Configuración del modo y tipo de archivo
            clienteFTP.enterLocalPassiveMode();
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // Obtener y mostrar el contenido del directorio raíz
            System.out.println("Contenido del directorio raíz:");
            FTPFile[] listaArchivos = clienteFTP.listFiles("/");
            if (listaArchivos != null && listaArchivos.length > 0) {
                for (FTPFile archivo : listaArchivos) {
                    System.out.println(archivo.getName());
                }
            } else {
                System.out.println("El directorio raíz está vacío.");
            }

            // Cerrar la sesión y desconectar
            clienteFTP.logout();
            clienteFTP.disconnect();
            System.out.println("Sesión cerrada y desconexión realizada correctamente.");
        } catch (IOException e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}