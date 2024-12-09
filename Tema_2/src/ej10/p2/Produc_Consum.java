package ej10.p2;

public class Produc_Consum {
    public static void main(String[] args) {
        Cola cola = new Cola();

        Productor productor = new Productor(cola);
        Consumidor consumidor = new Consumidor(cola);

        productor.start();
        consumidor.start();
    }
}
