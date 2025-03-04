package Ej5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ej5GUI extends JFrame {
    private JTextField txtServer, txtUsername, txtPassword, txtPort;
    private JButton btnConnect, btnRetrieveMessages;
    private JTextArea txtOutput;
    private POP3SClient pop3;

    public Ej5GUI() {
        // Configurar la ventana
        setTitle("Conexión POP3");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        txtServer = new JTextField("pop.gmail.com");
        txtUsername = new JTextField("traditionalland@gmail.com");
        txtPassword = new JPasswordField("yyjl ozmr ycxz rkgr");
        txtPort = new JTextField("995");  // Puerto por defecto
        btnConnect = new JButton("Conectar");
        btnRetrieveMessages = new JButton("Recuperar Mensajes");
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);

        // Configurar el layout
        setLayout(new GridLayout(7, 1));
        add(new JLabel("Servidor:"));
        add(txtServer);
        add(new JLabel("Usuario:"));
        add(txtUsername);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(new JLabel("Puerto:"));
        add(txtPort);
        add(btnConnect);
        add(btnRetrieveMessages);
        add(scrollPane);

        // Desactivar el botón de recuperación de mensajes hasta que se conecte
        btnRetrieveMessages.setEnabled(false);

        // Manejar evento de conexión
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        // Manejar evento de recuperación de mensajes
        btnRetrieveMessages.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retrieveMessages();
            }
        });
    }

    private void connectToServer() {
        String server = txtServer.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        int puerto = Integer.parseInt(txtPort.getText());  // Convertir el texto del puerto a entero

        pop3 = new POP3SClient(true);
        try {
            pop3.connect(server, puerto);
            txtOutput.append("Conexión realizada con el servidor\n");

            if (!pop3.login(username, password)) {
                txtOutput.append("Error al hacer login\n");
            } else {
                txtOutput.append("Login exitoso\n");
                btnRetrieveMessages.setEnabled(true);
            }

        } catch (IOException e) {
            txtOutput.append("Error de conexión: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void retrieveMessages() {
        try {
            POP3MessageInfo[] men = pop3.listMessages();

            if (men == null) {
                txtOutput.append("No se puede listar los mensajes\n");
            } else {
                txtOutput.append("Número de mensajes: " + men.length + "\n");
                recuperarMensajes(men);
            }
            pop3.logout();
            pop3.disconnect();
        } catch (IOException e) {
            txtOutput.append("Error al recuperar los mensajes: " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }

    private void recuperarMensajes(POP3MessageInfo[] mensajes) throws IOException {
        for (int i = 0; i < mensajes.length; i++) {
            txtOutput.append("\nMensaje " + (i + 1) + "\n");
            POP3MessageInfo msgInfo = mensajes[i];

            txtOutput.append("ID: " + msgInfo.identifier + "\n");
            txtOutput.append("Número: " + msgInfo.number + "\n");
            txtOutput.append("Tamaño: " + msgInfo.size + " bytes\n");

            POP3MessageInfo pmi = pop3.listUniqueIdentifier(i + 1);
            if (pmi != null) {
                txtOutput.append("Identificador único: " + pmi.identifier + "\n");
            } else {
                txtOutput.append("No se pudo obtener el identificador único.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Ej5GUI().setVisible(true);
            }
        });
    }
}
