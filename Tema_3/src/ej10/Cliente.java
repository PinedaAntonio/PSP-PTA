package ej10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClienteGUI();
        });
    }
}

class ClienteGUI extends JFrame {
    private JTextField idJugadorField;
    private JTextField numeroField;
    private JLabel intentosLabel;
    private JTextArea mensajesArea;
    private JButton enviarButton;
    private JButton salirButton;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private int intentos;

    public ClienteGUI() {
        //Configuración de la ventana principal
        setTitle("JUGADOR - ADIVINA UN NÚMERO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("ID JUGADOR:"));
        idJugadorField = new JTextField();
        inputPanel.add(idJugadorField);

        inputPanel.add(new JLabel("NÚMERO A ADIVINAR:"));
        numeroField = new JTextField();
        inputPanel.add(numeroField);

        intentosLabel = new JLabel("INTENTOS: 0");
        inputPanel.add(intentosLabel);

        enviarButton = new JButton("Enviar");
        inputPanel.add(enviarButton);

        mensajesArea = new JTextArea();
        mensajesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(mensajesArea);

        salirButton = new JButton("Salir");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(salirButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //Configuración de conexión al servidor
        try {
            socket = new Socket("localhost", 6010);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Hilo para poder recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = reader.readLine()) != null) {
                        mensajesArea.append(mensaje + "\n");
                    }
                } catch (IOException e) {
                    mensajesArea.append("Error al leer del servidor\n");
                }
            }).start();
        } catch (IOException e) {
            mensajesArea.append("No se pudo conectar al servidor\n");
        }

        //Acción del botón Enviar
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numero = numeroField.getText();
                if (!numero.isEmpty()) {
                    writer.println(numero);
                    intentos++;
                    intentosLabel.setText("INTENTOS: " + intentos);
                }
            }
        });

        //Acción del botón Salir
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void dispose() {
        super.dispose();
        try {
            if (socket != null) socket.close();
            if (writer != null) writer.close();
            if (reader != null) reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}