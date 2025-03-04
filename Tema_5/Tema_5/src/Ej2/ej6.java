package Ej2;

import java.io.*;
import java.security.MessageDigest;

public class ej6 {
    public static void main(String args[]) {
        try {
            FileInputStream fileout = new FileInputStream("DATOS.DAT");
            ObjectInputStream dataOS = new ObjectInputStream(fileout);

            String datos = (String) dataOS.readObject();
            System.out.println("Datos: " + datos);

            byte resumenOriginal[] = (byte[]) dataOS.readObject();

            //Esto es para cambiar a mayúsculas la lectura del DATOS.DAT. Comenta esta parte para que no se cambie nada del archivo
            datos = datos.toUpperCase();

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(datos.getBytes());
            byte resumenActual[] = md.digest();

            if (MessageDigest.isEqual(resumenActual, resumenOriginal))
                System.out.println("DATOS VÁLIDOS");
            else
                System.out.println("DATOS NO VÁLIDOS");

            dataOS.close();
            fileout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

