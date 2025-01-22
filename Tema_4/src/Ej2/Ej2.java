package Ej2;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Ej2 {

    public static void main(String[] args) {
        // Configuración del servidor FTP
        String servidor = "localhost";  // Dirección del servidor FTP
        int puerto = 21;                // Puerto por defecto para FTP
        String usuario = "Usuario1";    // Usuario configurado en el servidor FTP
        String contrasena = "usu1"; // Contraseña configurada para el usuario
        String directorioRemoto = "/";  // Directorio raíz en el servidor FTP

        // Crear JFileChooser para seleccionar el archivo a subir
        JFileChooser selectorDeArchivo = new JFileChooser();
        selectorDeArchivo.setDialogTitle("Elige un archivo para cargar al servidor FTP");
        int opcion = selectorDeArchivo.showOpenDialog(null);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = selectorDeArchivo.getSelectedFile();

            // Conexión al servidor FTP
            FTPClient clienteFTP = new FTPClient();
            try {
                clienteFTP.connect(servidor, puerto);
                boolean accesoExitoso = clienteFTP.login(usuario, contrasena);

                if (accesoExitoso) {
                    clienteFTP.enterLocalPassiveMode(); // Establecer modo pasivo
                    clienteFTP.setFileType(FTP.BINARY_FILE_TYPE); // Establecer el tipo de archivo como binario

                    // Cambiar al directorio raíz (si es necesario)
                    if (!clienteFTP.changeWorkingDirectory(directorioRemoto)) {
                        JOptionPane.showMessageDialog(null, "No se pudo acceder al directorio en el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Subir el archivo seleccionado
                    String archivoRemoto = archivoSeleccionado.getName(); // El nombre del archivo remoto será igual al del archivo local
                    try (FileInputStream flujoEntrada = new FileInputStream(archivoSeleccionado)) {
                        boolean archivoSubido = clienteFTP.storeFile(archivoRemoto, flujoEntrada); // Subir el archivo
                        if (archivoSubido) {
                            JOptionPane.showMessageDialog(null, archivoRemoto + " se ha subido correctamente.");

                            // Listar archivos en el directorio raíz del servidor FTP
                            System.out.println("Archivos en el directorio raíz del servidor FTP:");
                            for (String archivo : clienteFTP.listNames()) {
                                System.out.println(archivo);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Hubo un error al intentar subir el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión en el servidor FTP.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                clienteFTP.logout(); // Cerrar sesión después de la carga
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            } finally {
                try {
                    if (clienteFTP.isConnected()) {
                        clienteFTP.disconnect(); // Desconectar al finalizar
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo.");
        }
    }
}