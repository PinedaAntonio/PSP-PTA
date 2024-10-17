package Ej8;

import java.io.*;

public class Ej8 {
    public static void main(String[] args) throws IOException {
        // Ruta de los archivos
        File inputFile = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej8/entrada.txt");  // Archivo de entrada 
        File outputFile = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej8/salida.txt");  // Archivo de salida estándar
        File errorFile = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej8/error.txt");    // Archivo de salida de error

        // Creamos ProcessBuilder para ejecutar este mismo programa
        ProcessBuilder pb = new ProcessBuilder("java", "LeerNombre"); // Ejecuta Ejemplo5 de nuevo

        // Redirigimos la entrada desde el archivo entrada.txt
        pb.redirectInput(inputFile);

        // Redirigimos la salida estándar a salida.txt
        pb.redirectOutput(outputFile);

        // Redirigimos la salida de error a error.txt
        pb.redirectError(errorFile);

        // Iniciamos el proceso
        Process p = pb.start();

        // Esperamos a que termine el proceso
        try {
            int exitCode = p.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
