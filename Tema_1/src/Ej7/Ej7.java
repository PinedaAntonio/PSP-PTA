package Ej7;

import java.io.*;

//Se toma una entrada desde un fichero de texto y se escriben los errores y la salida producidos en su fichero correspondiente

public class Ej7 {
    public static void main(String[] args) {
        try {
            // Especificar la ruta completa de los archivos
            File input = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej7/entrada.txt");  // Archivo de entrada
            File output = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej7/salida.txt");  // Archivo de salida estándar
            File error = new File("/home/usuario/Escritorio/PSP/Tema_1/src/Ej7/error.txt");    // Archivo de salida de error

            // Redirigir la salida estándar y la salida de error a los archivos
            PrintStream salida = new PrintStream(new FileOutputStream(output));
            PrintStream errorSalida = new PrintStream(new FileOutputStream(error));
            System.setOut(salida);
            System.setErr(errorSalida);

            // Leemos el archivo de entrada y la procesamos
            BufferedReader br = new BufferedReader(new FileReader(input));
            String texto;
            while ((texto = br.readLine()) != null) {
                System.out.println("Cadena escrita: " + texto);  // Escribimos en salida.txt
            }

            // Cerramos los flujos
            br.close();
            salida.close();
            errorSalida.close();

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
