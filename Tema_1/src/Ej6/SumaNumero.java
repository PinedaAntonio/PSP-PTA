package Ej6;

//En este programa leemos dos números por entrada estándar y mostramos su suma una vez introducidos.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumaNumero {

    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);

        try {
            System.out.println("Ingresa el primer número: ");
            int num1 = Integer.parseInt(br.readLine());

            System.out.println("Ingresa el segundo número: ");
            int num2 = Integer.parseInt(br.readLine());

            int suma = num1 + num2;

            System.out.println("La suma es: " + suma);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}