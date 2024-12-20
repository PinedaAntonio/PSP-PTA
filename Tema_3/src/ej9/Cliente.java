package ej9;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 44444;
    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public Cliente() {
        // Crear el marco principal
        JFrame frame = new JFrame("Cliente - Ejercicio 5");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        frame.add(panelPrincipal);

        // Área de texto para mostrar los mensajes
        JTextArea areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollArea = new JScrollPane(areaTexto);
        panelPrincipal.add(scrollArea, BorderLayout.CENTER);

        // Panel inferior para entrada de texto y botones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        // Campo de texto para ingresar mensajes
        JTextField campoTexto = new JTextField();
        panelInferior.add(campoTexto, BorderLayout.CENTER);

        // Panel para los botones alineados a la derecha
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.add(panelBotones, BorderLayout.EAST);

        // Botón "Enviar"
        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setPreferredSize(new Dimension(75, 25));
        panelBotones.add(botonEnviar);

        // Botón "Limpiar"
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.setPreferredSize(new Dimension(75, 25));
        panelBotones.add(botonLimpiar);

        // Botón "Salir"
        JButton botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(75, 25));
        panelBotones.add(botonSalir);

        // Mostrar el JFrame
        frame.setVisible(true);

        // Configuración de los eventos de los botones
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoTexto.getText();
                if (!texto.isEmpty()) {
                    enviarTexto(texto);
                    campoTexto.setText(""); // Limpiar el campo después de enviar
                }
            }
        });

        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTexto.setText("");
                areaTexto.setText(""); // Limpiar el área de texto
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarTexto("*"); // Enviar "*" al servidor para finalizar
                cerrarConexion();
                frame.dispose(); // Cerrar la ventana del cliente
            }
        });

        // Conexión al servidor
        try {
            socket = new Socket(HOST, PUERTO);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String respuesta;
                    while ((respuesta = entrada.readLine()) != null) {
                        areaTexto.append(respuesta + "\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }).start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error al conectar con el servidor.", "Error", JOptionPane.ERROR_MESSAGE);
            frame.dispose();
        }
    }

    private void enviarTexto(String texto) {
        if (salida != null) {
            salida.println(texto);
        }
    }

    private void cerrarConexion() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cliente::new);
    }
}