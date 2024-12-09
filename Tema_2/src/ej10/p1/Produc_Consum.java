package ej10.p1;

public class Produc_Consum {
    public static void main(String[] args) {
        Cola cola = new Cola();

        Productor productor = new Productor(cola, 1);
        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);

        productor.start();
        consumidor1.start();
        consumidor2.start();
    }
}
