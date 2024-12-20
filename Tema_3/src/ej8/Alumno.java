package ej8;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String idAlumno;
    private String nombre;
    private Curso curso;
    private double nota;

    public Alumno(String idAlumno, String nombre, Curso curso, double nota) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    public String getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Alumno{idAlumno='" + idAlumno + "', nombre='" + nombre + "', curso=" + curso + ", nota=" + nota + "}";
    }
}
