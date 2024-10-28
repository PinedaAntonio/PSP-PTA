package ej4;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Ej8Main {
    public static void main(String[] args) {
        //Para ejecutar el Ejercicio 8
        ContadorEj8 applet = new ContadorEj8();

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
