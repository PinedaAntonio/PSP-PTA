package ej12;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class MovimientoLetra extends Applet implements Runnable, ActionListener {
    private Thread hilo;
    private boolean detener;
    private int x, y, direccion;
    private Font fuente;
    private Button botonFinalizar;

    @Override
    public void init() {
        setBackground(Color.white);

        x = 1;
        y = 100;
        direccion = 1;

        fuente = new Font("Serif", Font.BOLD, 30);

        botonFinalizar = new Button("Finalizar Hilo");
        add(botonFinalizar);
        botonFinalizar.addActionListener(this);
    }

    @Override
    public void start() {
        // Iniciar el hilo de animación
        if (hilo == null || !hilo.isAlive()) {
            hilo = new Thread(this);
            hilo.start();
        }
    }

    @Override
    public void run() {
        detener = false;
        while (!detener) {
            x += direccion;

            if (x >= getSize().width - 30) {
                direccion = -1;
            } else if (x <= 1) {
                direccion = 1;
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.setFont(fuente);
        g.setColor(Color.blue);
        g.drawString("A", x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonFinalizar) {
            if (hilo != null && hilo.isAlive()) {
                detener = !detener;
                if (detener) {
                    botonFinalizar.setLabel("Reanudar Hilo");
                } else {
                    botonFinalizar.setLabel("Finalizar Hilo");
                    start();
                }
            }
        }
    }

    @Override
    public void stop() {
        hilo = null; // Finalizar el hilo
    }
}
