package ej11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread {
    DataInputStream fentrada;
    Socket socket;
    ComunHilos comun;
    boolean ejecutando = true;

    public HiloServidorChat(Socket socket, ComunHilos comun) {
        this.socket = socket;
        this.comun = comun;
        try {
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Error al obtener el flujo de entrada del cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Número de conexiones actuales: " + comun.getActuales());

        EnviarMensaje(comun.getMensajes());

        while (ejecutando) {
            try {
                String cadena = fentrada.readUTF();
                if (cadena.trim().equals("*")) {
                    comun.setActuales(comun.getActuales() - 1);
                    System.out.println("Número de conexiones actuales tras salida: " + comun.getActuales());
                    ejecutando = true;
                } else {
                    comun.setMensajes(comun.getMensajes() + cadena + "\n");
                    EnviarMensaje(comun.getMensajes());
                }
            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                ejecutando = false;
            }
        }

        cerrarSocket();
    }

    private void EnviarMensaje(String mensaje) {
        for (int i = 0; i < comun.getConexiones(); i++) {
            Socket s1 = comun.getTabla(i);
            if (s1 != null && !s1.isClosed()) {
                try {
                    DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                    dos.writeUTF(mensaje);
                } catch (IOException e) {
                    System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
                }
            }
        }
    }

    private void cerrarSocket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }
    }
}