package ej2;

import java.util.ArrayList;
import java.util.Arrays;

public class Profesor {
    int idProfesor;
    String nombre;
    Asignatura[] asignatura = new Asignatura[3];
    Especialidad especialidad;

    public Profesor(int idProfesor, String nombre, Asignatura[] asignatura, Especialidad especialidad) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.especialidad = especialidad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura[] asignatura) {
        this.asignatura = asignatura;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Especialidad: " + especialidad + '\'' + Arrays.toString(asignatura);
    }
}
