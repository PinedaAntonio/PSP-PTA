package ej8;

public class Arbitro {
    private final int numeroAAdivinar;
    private final int totalJugadores;
    private int turnoActual;
    private boolean juegoTerminado;

    public Arbitro(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        this.numeroAAdivinar = 1 + (int) (10 * Math.random());
        this.turnoActual = 1; // El primer turno corresponde al jugador 1
        this.juegoTerminado = false;

        System.out.println("NÚMERO A ADIVINAR: " + numeroAAdivinar);
    }


    public synchronized int getTurnoActual() {
        return turnoActual;
    }

    public synchronized boolean comprobarJugada(int idJugador, int numero) {
        if (juegoTerminado) {
            return true;
        }

        if (idJugador == turnoActual) {
            System.out.println("Le toca a Jugador " + idJugador);
            System.out.println("Jugador" + idJugador + " dice: " + numero);

            if (numero == numeroAAdivinar) {
                System.out.println("Jugador" + idJugador + " gana, enhorabuena!!!");
                juegoTerminado = true;
                return true;
            } else {
                turnoActual = (turnoActual % totalJugadores) + 1;
            }
        }

        return false;
    }

    public synchronized boolean isJuegoTerminado() {
        return juegoTerminado;
    }
}
