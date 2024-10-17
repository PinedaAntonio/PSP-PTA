package Ej4;

//Este programa lee un argumento que le hayamos pasado y nos lo muestra por pantalla. En caso de que no haya ningún argumento, nos pedirá que introduzcamos uno la próxima vez.

import java.io.IOException;

public class LeerNombre {
    public static void main(String[] args) throws IOException {
        if(args.length <= 0){
            System.out.println("No hay argumento que leer, introduce uno la próxima vez");
            System.exit(-1);
        } else {
            System.out.println(args[0]);
            System.exit(1);
        }
    }
}