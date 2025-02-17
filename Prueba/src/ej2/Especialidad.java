package ej2;

public class Especialidad {
    int id;
    String nombreespe;

    public Especialidad(int id, String nombreespe) {
        this.id = id;
        this.nombreespe = nombreespe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreespe() {
        return nombreespe;
    }

    public void setNombreespe(String nombreespe) {
        this.nombreespe = nombreespe;
    }

    @Override
    public String toString() {
        return "Especialidad: " + id + " - " + nombreespe + '\'';
    }
}
