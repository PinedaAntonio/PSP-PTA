package ej8;

public class Main {
    public static void main(String[] args) {
        int totalJugadores = 3;

        Arbitro arbitro = new Arbitro(totalJugadores);

        for (int i = 1; i <= totalJugadores; i++) {
            new Jugador(i, arbitro).start();
        }
    }
}
