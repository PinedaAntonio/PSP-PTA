package Ej4;

//Este programa solo llama a LeerNombre y lo ejecuta. Tras eso, muestra la salida del proceso de LeerNombre

import java.io.*;

public class ProbarNombre {

    public static void main(String[] args) throws IOException {

        File directorio = new File("/home/usuario/Escritorio/PSP/Tema_1/out/production/Tema_1");

        ProcessBuilder pb = new ProcessBuilder("java", "Ej4.LeerNombre", "Ejemplo para procesos");
        pb.directory(directorio);

        // Iniciamos el proceso
        Process p = pb.start();

        // Leemos y mostramos la salida del proceso
        try (InputStream in = p.getInputStream()) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Mostramos el valor de salida del proceso
        try {
            int exitVal = p.waitFor();
            System.out.println("Valor de salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
