package ej8;

public class Jugador extends Thread {
    private final int idJugador;
    private final Arbitro arbitro;

    public Jugador(int idJugador, Arbitro arbitro) {
        this.idJugador = idJugador;
        this.arbitro = arbitro;
    }

    @Override
    public void run() {
        while (!arbitro.isJuegoTerminado()) {
            if (arbitro.getTurnoActual() == idJugador) {
                int numero = 1 + (int) (10 * Math.random());
                boolean terminado = arbitro.comprobarJugada(idJugador, numero);

                if (terminado) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
