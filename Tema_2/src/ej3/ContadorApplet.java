package ej3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;


public class ContadorApplet extends Applet implements Runnable, ActionListener{
    private Thread h;
    long contador = 0;
    private boolean parar;
    private Font fuente;
    private Button b1, b2;

    public void start() {

    }

    public void init(){
        setBackground(Color.yellow);
        add(b1=new Button("Iniciar contador"));
        b1.addActionListener(this);
        add(b2=new Button("Finalizar contador"));
        b2.addActionListener(this);
        fuente = new Font("Serif", Font.BOLD, 26);
    }

    public void run() {
        parar = false;
        Thread hiloActual = Thread.currentThread();
        while (h == hiloActual && !parar){
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
            contador++;
        }
    }

    public void paint(Graphics g){
        g.clearRect(0,0,400,400);
        g.setFont(fuente);
        g.drawString(Long.toString((long)contador),80,100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            b1.setLabel("Continuar");
            if(h != null && h.isAlive()){

            }else {
                h = new Thread(this);
                h.start();
            }
        } else if (e.getSource() == b2) {
            parar = true;
        }
    }

    @Override
    public void stop() {
        h = null;
    }
}
