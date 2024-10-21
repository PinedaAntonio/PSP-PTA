package ej3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

// Clase que extiende Thread y actúa como un contador
class HiloContador extends Thread {
    private long contador;
    private boolean parar;
    private ContadorEj3 applet; // Referencia al applet para llamar a repaint()

    public HiloContador(long contadorInicial, ContadorEj3 applet) {
        this.contador = contadorInicial;
        this.applet = applet;
        this.parar = false;
    }

    public void detener() {
        parar = true;
    }

    public long getContador() {
        return contador;
    }

    @Override
    public void run() {
        while (!parar) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++;
            applet.repaint();  // Actualizamos la interfaz llamando a repaint
        }
    }
}

public class ContadorEj3 extends Applet implements ActionListener {
    private HiloContador hilo1, hilo2;
    private Font fuente;
    private Button bIniciar1, bDetener1, bIniciar2, bDetener2;

    @Override
    public void init() {
        setBackground(Color.yellow);
        add(bIniciar1 = new Button("Iniciar contador 1"));
        bIniciar1.addActionListener(this);
        add(bDetener1 = new Button("Detener contador 1"));
        bDetener1.addActionListener(this);

        add(bIniciar2 = new Button("Iniciar contador 2"));
        bIniciar2.addActionListener(this);
        add(bDetener2 = new Button("Detener contador 2"));
        bDetener2.addActionListener(this);

        fuente = new Font("Serif", Font.BOLD, 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bIniciar1) {
            if (hilo1 == null || !hilo1.isAlive()) {
                hilo1 = new HiloContador(0, this); // Inicia con un valor de 0
                hilo1.start();
                bDetener1.setLabel("Detener contador 1");
            }
        } else if (e.getSource() == bDetener1) {
            if (hilo1 != null) {
                hilo1.detener();
                bDetener1.setLabel("Hilo1 Finalizado");
            }
        } else if (e.getSource() == bIniciar2) {
            if (hilo2 == null || !hilo2.isAlive()) {
                hilo2 = new HiloContador(50, this); // Inicia con un valor de 100
                hilo2.start();
                bDetener2.setLabel("Detener contador 2");
            }
        } else if (e.getSource() == bDetener2) {
            if (hilo2 != null) {
                hilo2.detener();
                bDetener2.setLabel("Hilo2 Finalizado");
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        // Muestra el valor de cada contador
        g.drawString("Contador 1: " + (hilo1 != null ? hilo1.getContador() : 0), 50, 100);
        g.drawString("Contador 2: " + (hilo2 != null ? hilo2.getContador() : 100), 50, 150);
    }
}

