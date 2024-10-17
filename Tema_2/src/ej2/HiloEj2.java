package ej2;

public class HiloEj2 implements Runnable {
    private String cad;

    public String getCad() {
        return cad;
    }

    public HiloEj2(String s) {
        this.cad = s;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(Thread.currentThread().getId() * 500);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Hola Mundo " + getCad() + " Hilo_id=" + Thread.currentThread().getId());
    }
}
