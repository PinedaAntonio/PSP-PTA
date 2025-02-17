package ej1;

//La clase Cuenta es a la que acceden las clases Persona para poder hacer ingresos y reintegros

public class Cuenta {
    private int saldo = 0;
    private int maximo = 0;

    Cuenta(int saldo, int maximo) throws InterruptedException {
        this.saldo = saldo;
        this.maximo = maximo;
    }

    //Con un synchronized nos aseguramos de que estos ingresos y reintegros se mantengan para la siguiente persona que acceda a la cuenta
    public synchronized void ingreso(int cantidad, String nombre) throws InterruptedException {
        int saldo_prov = saldo + cantidad;
        //Mostramos antes la operación para aclarar qué cantidad se sumaría si fuera posible
        System.out.println(nombre + ": Insertando (" + cantidad + ") euros. Saldo anterior: " + saldo);
        if(saldo_prov <= maximo){ //Si el saldo es mayor al máximo, no se hace la operación
            saldo = saldo_prov;
            System.out.println(nombre + ": Saldo actual: " + saldo);
        } else {
            System.out.println(nombre + ": El saldo sería superior al límite, transacción no realizada");
        }
        Thread.currentThread().sleep(2000);
    }

    public synchronized void reintegro(int cantidad, String nombre) throws InterruptedException {
        int saldo_prov = saldo - cantidad;
        //Mostramos antes la operación para aclarar qué cantidad se restaría si fuera posible
        System.out.println(nombre + ": Reintegrando (" + cantidad + ") euros. Saldo anterior: " + saldo);
        if(saldo_prov >= 0){ //Si el saldo es menor a 0, no se hace la operación
            saldo = saldo_prov;
            System.out.println(nombre + ": Saldo actual: " + saldo);
        } else {
            System.out.println(nombre + ": El saldo sería inferior a 0, transacción no realizada");
        }
        Thread.currentThread().sleep(2000);
    }

    public synchronized int get() throws InterruptedException {
        return saldo;
    }
}
