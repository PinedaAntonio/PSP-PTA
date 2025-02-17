package ej2;

//Este servidor tendrá un bucle infinito que devolverá a los clientes su id de cliente y los datos del profesor que pidan
//La inicialización de los profesores se realiza en este mismo servidor

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int puerto = 6000;
        ServerSocket serverSocket = new ServerSocket(puerto);

        int cliente_actual = 1;

        //Asignaturas profesor 1
        Asignatura[] asignaturas1 = new Asignatura[3];
        asignaturas1[0] = new Asignatura(1, "Mates");

        //Especialidad profesor 1
        Especialidad especialidad1 = new Especialidad(1, "Mates");

        //Asignaturas profesor 2
        Asignatura[] asignaturas2 = new Asignatura[3];
        asignaturas2[0] = new Asignatura(1, "Mates");
        asignaturas2[1] = new Asignatura(2, "Lengua");
        asignaturas2[2] = new Asignatura(3, "Historia");

        //Especialidad profesor 2
        Especialidad especialidad2 = new Especialidad(3, "Historia");

        //Asignaturas profesor 3
        Asignatura[] asignaturas3 = new Asignatura[3];
        asignaturas3[0] = new Asignatura(4, "Inglés");
        asignaturas3[1] = new Asignatura(5, "Física");

        //Especialidad profesor 3
        Especialidad especialidad3 = new Especialidad(4, "Inglés");

        //Asignaturas profesor 4
        Asignatura[] asignaturas4 = new Asignatura[3];
        asignaturas4[0] = new Asignatura(6, "Religión");
        asignaturas4[1] = new Asignatura(2, "Lengua");

        //Especialidad profesor 4
        Especialidad especialidad4 = new Especialidad(2, "Lengua");

        //Asignaturas profesor 5
        Asignatura[] asignaturas5 = new Asignatura[3];
        asignaturas5[0] = new Asignatura(1, "Mates");
        asignaturas5[1] = new Asignatura(5, "Física");
        asignaturas5[2] = new Asignatura(6, "Religión");

        //Especialidad profesor 5
        Especialidad especialidad5 = new Especialidad(5, "Física");

        Profesor[] profesores = new Profesor[5];
        profesores[0] = new Profesor(1, "Rafa", asignaturas1, especialidad1);
        profesores[1] = new Profesor(2, "Jaime", asignaturas2, especialidad2);
        profesores[2] = new Profesor(3, "Loli", asignaturas3, especialidad3);
        profesores[3] = new Profesor(4, "Juan", asignaturas4, especialidad4);
        profesores[4] = new Profesor(5, "María", asignaturas5, especialidad5);

        System.out.println("Servidor iniciado...");

        while (true) {
            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            //Enviamos el número de cliente
            System.out.println("Cliente " + cliente_actual + " conectado");
            out.writeUTF("Soy el cliente: " + cliente_actual);

            //Recibimos el mensaje del cliente
            int m2 = in.readInt();
            System.out.println("Consultando id: " + m2 + ", solicitado por cliente " + cliente_actual);

            if(m2 == 0){ //Si recibe un 0, se cierra la conexión con ese cliente
                System.out.println("Cliente " + cliente_actual + " desconectado...");
                socket.close();
                cliente_actual++;
            }else{
                if(m2 > 1 && m2 < 5){
                    Profesor profEnc = profesores[m2];
                    out.writeUTF(profEnc.toString());
                } else{
                    out.writeUTF("No hay profesores con el id: " + m2);
                }
            }

        }
    }
}


