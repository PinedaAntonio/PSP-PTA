package Ej1;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Ej1 {
    public static void main(String[] args) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            String texto = "Esto es un texto plano";

            byte dataBytes[] = texto.getBytes();
            md.update(dataBytes);
            byte[] resumen = md.digest();

            System.out.println("Mensaje original: " + texto);
            System.out.println("Número de bytes: " + md.getDigestLength());
            System.out.println("Algoritmo: " + md.getAlgorithm());
            System.out.println("Mensaje resumen: " + new String(resumen));
            System.out.println("Mensaje en hexadecimal: " + Hexadecimal(resumen));
            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: " + proveedor.toString());
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }
    static String Hexadecimal(byte[] resumen){
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) hex +=0;
            hex += h;
        }return hex.toUpperCase();
    }
}
