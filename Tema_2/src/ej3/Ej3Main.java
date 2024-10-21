package ej3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Ej3Main {
    public static void main(String[] args) {
        //Para ejecutar el Ejercicio 3, COMENTAR EL OTRO ANTES
        ContadorEj3 applet = new ContadorEj3();
        //Para ejecutar el ContadorApplet, COMENTAR EL OTRO ANTES
        //ContadorApplet applet = new ContadorApplet();
        Frame frame = new Frame("Contador");
        frame.setSize(400, 400);
        frame.add(applet);
        frame.setVisible(true);

        applet.init();
        applet.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                applet.stop();
                applet.destroy();
                frame.dispose();
            }
        });
    }
}
