package Ej5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import org.apache.commons.net.pop3.*;

public class Ej3_IG extends JFrame {
    private JTextField fieldPassword, fieldUsername, fieldPort, fieldServer;
    private JButton btnConectar, btnCargarMensajes;
    private JTextArea areaOutput;
    private POP3SClient pop3;

    public Ej3_IG() {
        //Configuración de la ventana
        setTitle("Conexión POP3");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Creación de los componentes
        fieldServer = new JTextField("pop.gmail.com");
        fieldUsername = new JTextField("antoniopinedatoro@gmail.com");
        fieldPassword = new JPasswordField("swhq mkek ihce viuc");
        fieldPort = new JTextField("995");  // Puerto por defecto
        btnConectar = new JButton("Conectar");
        btnCargarMensajes = new JButton("Recuperar Mensajes");
        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaOutput);

        //Configuración del layout
        setLayout(new GridLayout(7, 1));
        add(new JLabel("Servidor:"));
        add(fieldServer);
        add(new JLabel("Usuario:"));
        add(fieldUsername);
        add(new JLabel("Contraseña:"));
        add(fieldPassword);
        add(new JLabel("Puerto:"));
        add(fieldPort);
        add(btnConectar);
        add(btnCargarMensajes);
        add(scrollPane);

        //Desactivar el botón de mensajes hasta conectarse
        btnCargarMensajes.setEnabled(false);

        //Acción para conectar al servidor
        btnConectar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conectar();
            }
        });

        //Acción para obtener los mensajes
        btnCargarMensajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarMensajes();
            }
        });
    }

    private void conectar() {
        String server = fieldServer.getText();
        String username = fieldUsername.getText();
        String password = fieldPassword.getText();
        int puerto = Integer.parseInt(fieldPort.getText());

        pop3 = new POP3SClient(true);
        try {
            pop3.connect(server, puerto);
            areaOutput.append("Conexión realizada con el servidor\n");

            if (!pop3.login(username, password)) {
                areaOutput.append("Error al hacer login\n");
            } else {
                areaOutput.append("Login exitoso\n");
                btnCargarMensajes.setEnabled(true);
            }

        } catch (IOException e) {
            areaOutput.append("Error de conexión: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void cargarMensajes() {
        try {
            POP3MessageInfo[] msgInfo = pop3.listMessages();

            if (msgInfo == null) {
                areaOutput.append("No se puede listar los mensajes\n");
            } else {
                areaOutput.append("Número de mensajes: " + msgInfo.length + "\n");
                detallesMensajes(msgInfo);
            }
            pop3.logout();
            pop3.disconnect();
        } catch (IOException e) {
            areaOutput.append("Error al recuperar los mensajes: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void detallesMensajes(POP3MessageInfo[] mensajes) throws IOException {
        for (int i = 0; i < mensajes.length; i++) {
            areaOutput.append("\nMensaje " + (i + 1) + "\n");
            POP3MessageInfo msgInfo = mensajes[i];

            areaOutput.append("ID: " + msgInfo.identifier + "\n");
            areaOutput.append("Número: " + msgInfo.number + "\n");
            areaOutput.append("Tamaño: " + msgInfo.size + " bytes\n");

            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
            if (pmi != null) {
                areaOutput.append("Identificador único: " + pmi.identifier + "\n");
            } else {
                areaOutput.append("No ha sido posible obtener el identificador único.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ej3_IG().setVisible(true);
            }
        });
    }
}
