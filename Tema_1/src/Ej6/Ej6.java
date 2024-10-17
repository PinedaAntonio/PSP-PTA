package Ej6;

import java.io.*;

//Este programa llama a SumaNumero, pide dos números por teclado y los envía a SumaNumero, tras eso se imprime el resultado de SumaNumero por consola
public class Ej6 {

    public static void main(String[] args) throws IOException {

        // Establecemos el directorio donde está el archivo LeerNombre.class
        File directorio = new File("/home/usuario/Escritorio/PSP/Tema_1/out/production/Tema_1");

        // Valor 1
        ProcessBuilder pb = new ProcessBuilder("java", "Ej6.SumaNumero");


        // Establecemos el directorio donde buscar el archivo .class
        pb.directory(directorio);


        // Iniciamos el proceso
        Process p = pb.start();
        //Lee entrada de Ej6 por teclado
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //lee salida de SumaNumero
        BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream()));

        //Manda la entrada de ej6 a sumanumero
        OutputStream os = p.getOutputStream();

        //Lee la linea del programa Sumanumero
        System.out.println(br2.readLine());
        //Lee lo que escribo por teclado
        String num1 = br.readLine();
        //Escribe en el otro programa
        os.write((num1 + "\n").getBytes());
        //Limpia el output stream
        os.flush();


        //Lee la linea del programa Sumanumero
        System.out.println(br2.readLine());
        //Lee lo que escribo por teclado
        String num2 = br.readLine();
        //Escribe en el otro programa
        os.write((num2 + "\n").getBytes());
        //Limpia el output stream
        os.flush();


        // Leemos y mostramos la salida del proceso
        try (InputStream in = p.getInputStream()) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}