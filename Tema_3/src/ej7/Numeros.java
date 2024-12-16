package ej7;

import java.io.*;

public class Numeros implements Serializable {
    int numero;
    long cuadrado;
    long cubo;

    // Constructor sin parámetros
    public Numeros() {
    }

    // Constructor con parámetros
    public Numeros(int numero) {
        this.numero = numero;
        this.cuadrado = (long) numero * numero;
        this.cubo = (long) numero * numero * numero;
    }

    // Getters y setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        this.cuadrado = (long) numero * numero;
        this.cubo = (long) numero * numero * numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }
}
