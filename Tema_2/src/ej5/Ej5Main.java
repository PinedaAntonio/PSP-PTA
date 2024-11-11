package ej5;

import java.awt.*;
import java.awt.event.*;

public class Ej5Main {
    public static void main(String[] args) {
        //Para ejecutar el Ejercicio 5, COMENTAR EL OTRO ANTES
        ContadorEj5 applet = new ContadorEj5();
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
