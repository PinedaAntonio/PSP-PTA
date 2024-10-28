package ej4;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

// Clase que extiende Thread y actúa como un contador
class HiloContador extends Thread {
    private int contador;
    private boolean parar;
    private ContadorEj8 applet;
    public boolean running = true;
    public SolicitaSuspender suspender = new SolicitaSuspender();// Referencia al applet para llamar a repaint()

    public HiloContador(int contadorInicial, ContadorEj8 applet) {
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

public class ContadorEj8 extends Applet implements ActionListener {
    private HiloContador hilo1, hilo2;
    private Font fuente;
    private Button bSuspender1, bSuspender2, bFinalizar, bComenzar;

    @Override
    public void init() {
        setBackground(Color.yellow);
        add(bSuspender1 = new Button("Suspender contador 1"));
        bSuspender1.addActionListener(this);

        add(bSuspender2 = new Button("Suspender contador 2"));
        bSuspender2.addActionListener(this);

        fuente = new Font("Serif", Font.BOLD, 26);
    }

    @Override
    public void start() {
        hilo1 = new HiloContador(0, this); // Inicia con un valor de 0
        hilo1.start();
        hilo2 = new HiloContador(100, this); // Inicia con un valor de 100
        hilo2.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bComenzar) {
            if(hilo1.getContador() == 0 && hilo2.getContador() == 0) {
                hilo1.start();
                hilo2.start();
                bComenzar.setEnabled(false);
            }
        } else if (e.getSource() == bSuspender1) {
            if (hilo1 != null) {
                hilo1.();
                bSuspender1.setLabel("Hilo1 Suspendido");
            }
        } else if (e.getSource() == bSuspender2) {
            if (hilo2 != null) {
                hilo2.detener();
                bSuspender2.setLabel("Hilo2 Suspendido");
            }
        } else if (e.getSource() == bFinalizar) {
            if (hilo1 != null && hilo2 != null) {
                hilo1.detener();
                hilo2.detener();
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




