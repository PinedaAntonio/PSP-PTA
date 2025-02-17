package ej1;

//Esta clase Persona es la que accede a la cuenta para poder hacer ingresos o reintegros
//Lanzando dos clases personas en el main, hacemos que dos personas accedan a la misma cuenta
import java.util.Scanner;

public class Persona extends Thread {
    private Cuenta cuenta;
    public String nombre;
    private int dormir; //esto solo se usará en la segunda clase Persona, asegurando que los datos se recojan bien

    public Persona(Cuenta cuenta, String nombre) {
        this.cuenta = cuenta;
        this.nombre = nombre;
    }

    public Persona(Cuenta cuenta, String nombre, int dormir) {
        this.cuenta = cuenta;
        this.nombre = nombre;
        this.dormir = dormir;
    }

    @Override
    public void run() {
        try {
            currentThread().sleep(dormir);
            System.out.println(nombre + ": Saldo inicial: " + cuenta.get()); //sleep para asegurar que no se solapen las dos Personas
            int r1 = (int) (Math.random() * 500 + 1);
            int r2 = (int) (Math.random() * 500 + 1);
            int r3 = (int) (Math.random() * 500 + 1);
            int r4 = (int) (Math.random() * 500 + 1);

            cuenta.ingreso(r1, nombre);
            cuenta.reintegro(r2, nombre);
            cuenta.ingreso(r3, nombre);
            cuenta.reintegro(r4, nombre);

            System.out.println(nombre + ": Saldo final = " + cuenta.get());
            currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        int saldo = 0;
        int maximo = 0;
        System.out.println("Introduzca el saldo de la cuenta: (número entero)");
        saldo = sc.nextInt();
        System.out.println("Introduzca el saldo máximo de la cuenta: (número entero)");
        maximo = sc.nextInt();
        Cuenta cuenta = new Cuenta(saldo, maximo);
        Persona a = new Persona(cuenta, "Pepi"); //Primera persona llamada Pepi
        Persona b = new Persona(cuenta, "Paco", 10000); //Segunda persona llamad Paco

        a.start();
        b.start();
    }
}

