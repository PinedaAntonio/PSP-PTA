package ej5;

import ej4.SolicitaSuspender;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

// Clase que extiende Thread y actúa como un contador
class Hilo extends Thread {
    public SolicitaSuspender suspender = new SolicitaSuspender();
    public int contador = 0;
    public ContadorEj9 applet;

    public Hilo(int i, ContadorEj9 applet) {
        this.contador = i;
        this.applet = applet;
    }

    public void Suspende() {
        suspender.set(true);
    }

    public void Reanuda() {
        suspender.set(false);
    }

    // Método que interrumpe el hilo
    public void detener() {
        suspender.set(false); // Asegura que el hilo esté reanudado
        this.interrupt();     // Interrumpe el hilo
    }

    public void mostrar() {
        System.out.println("Valor del contador: " + contador);
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) { // Usa isInterrupted() para continuar mientras no esté interrumpido
                contador++;
                mostrar();
                Thread.sleep(1000);
                suspender.esperandoParaReanudar();
                applet.repaint();
            }
        } catch (InterruptedException e) {
            // El hilo termina su ejecución al capturar la excepción
            System.out.println("Hilo interrumpido y detenido.");
        }
        System.out.println("Valor final del contador: " + contador);
    }

    public long getContador() {
        return contador;
    }
}

public class ContadorEj9 extends Applet implements ActionListener {
    private Hilo hilo1, hilo2;
    private Font fuente;
    private Button bSuspender1, bSuspender2, bFinalizar, bComenzar;

    @Override
    public void init() {
        setBackground(Color.yellow);
        add(bComenzar = new Button("Comenzar"));
        bComenzar.addActionListener(this);

        add(bSuspender1 = new Button("Suspender contador 1"));
        bSuspender1.addActionListener(this);

        add(bSuspender2 = new Button("Suspender contador 2"));
        bSuspender2.addActionListener(this);

        add(bFinalizar = new Button("Finalizar contador"));
        bFinalizar.addActionListener(this);
        fuente = new Font("Serif", Font.BOLD, 26);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bComenzar) {
            if ((hilo1 == null || !hilo1.isAlive()) && (hilo2 == null || !hilo2.isAlive())) {
                hilo1 = new Hilo(0, this); // Inicia con un valor de 0
                hilo1.start();
                hilo2 = new Hilo(0, this); // Inicia con un valor de 0
                hilo2.start();
                bComenzar.setEnabled(false);
            }
        } else if (e.getSource() == bSuspender1) {
            if (hilo1 != null) {
                hilo1.detener();
                bSuspender1.setLabel("Hilo1 Detenido");
            }
        } else if (e.getSource() == bSuspender2) {
            if (hilo2 != null) {
                hilo2.detener();
                bSuspender2.setLabel("Hilo2 Detenido");
            }
        } else if (e.getSource() == bFinalizar) {
            if (hilo1 != null) {
                hilo1.detener();
                hilo1 = null;
            }
            if (hilo2 != null) {
                hilo2.detener();
                hilo2 = null;
            }
            repaint();
            bComenzar.setEnabled(true); // Habilita "Comenzar" después de finalizar ambos hilos
        }
    }

    @Override
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        // Muestra el valor de cada contador
        g.drawString("Contador 1: " + (hilo1 != null ? hilo1.getContador() : 0), 50, 100);
        g.drawString("Contador 2: " + (hilo2 != null ? hilo2.getContador() : 0), 50, 150);
    }
}
