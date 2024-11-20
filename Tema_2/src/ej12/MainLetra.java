package ej12;

import java.awt.*;
import java.awt.event.*;

public class MainLetra {
    public static void main(String[] args) {
        MovimientoLetra applet = new MovimientoLetra();

        Frame frame = new Frame("Movimiento Letra");
        frame.setSize(400, 200);
        frame.add(applet);
        frame.setVisible(true);

        applet.init();
        applet.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                applet.stop();
                frame.dispose();
            }
        });
    }
}
