package Ej3;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ej3 {

    private static FTPClient clienteFTP = new FTPClient();

    public static void main(String[] args) throws IOException {

        // Crear ventana principal
        JFrame ventana = new JFrame("GESTIONAR FICHEROS FTP");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(600, 400);
        ventana.setLayout(new BorderLayout());

        // Panel para ingresar usuario y contraseña
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridLayout(3, 2));
        JLabel etiquetaUsuario = new JLabel("Nombre de usuario:");
        JTextField campoUsuario = new JTextField("usuario1"); // Usuario predefinido
        JLabel etiquetaContrasena = new JLabel("Contraseña:");
        JPasswordField campoContrasena = new JPasswordField("contrasena1"); // Contraseña predefinida
        JButton botonConectar = new JButton("Conectar");
        panelLogin.add(etiquetaUsuario);
        panelLogin.add(campoUsuario);
        panelLogin.add(etiquetaContrasena);
        panelLogin.add(campoContrasena);
        panelLogin.add(new JLabel());
        panelLogin.add(botonConectar);

        ventana.add(panelLogin, BorderLayout.NORTH);

        // Lista de archivos y botones
        DefaultListModel<String> modeloListaArchivos = new DefaultListModel<>();
        JList<String> listaArchivos = new JList<>(modeloListaArchivos);
        JScrollPane panelDesplazamiento = new JScrollPane(listaArchivos);

        JButton botonDescargar = new JButton("Descargar archivo");
        JButton botonSalir = new JButton("Cerrar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonDescargar);
        panelBotones.add(botonSalir);

        ventana.add(panelDesplazamiento, BorderLayout.CENTER);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Acción para conectarse al servidor FTP
        botonConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contrasena = new String(campoContrasena.getPassword());
                try {
                    clienteFTP.connect("localhost", 21); // Dirección y puerto del servidor FTP
                    boolean loginExitoso = clienteFTP.login(usuario, contrasena);
                    clienteFTP.changeWorkingDirectory("/home/usuario");

                    if (loginExitoso) {
                        JOptionPane.showMessageDialog(ventana, "Conexión establecida correctamente.");
                        clienteFTP.enterLocalPassiveMode();

                        // Listar archivos disponibles
                        modeloListaArchivos.clear();
                        for (String archivo : clienteFTP.listNames()) {
                            modeloListaArchivos.addElement(archivo);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ventana, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ventana, "Fallo en la conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo
        botonDescargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String archivoSeleccionado = listaArchivos.getSelectedValue();
                if (archivoSeleccionado == null) {
                    JOptionPane.showMessageDialog(ventana, "Por favor, seleccione un archivo para descargar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Seleccionar directorio de destino
                JFileChooser selectorDirectorio = new JFileChooser();
                selectorDirectorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int resultado = selectorDirectorio.showSaveDialog(ventana);

                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File directorioDestino = selectorDirectorio.getSelectedFile();
                    File archivoLocal = new File(directorioDestino, archivoSeleccionado);

                    try (FileOutputStream flujoSalida = new FileOutputStream(archivoLocal)) {
                        boolean exito = clienteFTP.retrieveFile(archivoSeleccionado, flujoSalida);
                        if (exito) {
                            JOptionPane.showMessageDialog(ventana, "El archivo ha sido descargado en: " + archivoLocal.getAbsolutePath());
                        } else {
                            JOptionPane.showMessageDialog(ventana, "Error al intentar descargar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(ventana, "Error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir de la aplicación
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (clienteFTP.isConnected()) {
                        clienteFTP.logout();
                        clienteFTP.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                ventana.dispose();
            }
        });

        ventana.setVisible(true);
    }
}