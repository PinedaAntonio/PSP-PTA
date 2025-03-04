package Ej5;

import javax.security.auth.login.*;
import javax.security.auth.callback.*;

public class MainJaasAutenticacion {
    public static void main(String[] args) {
        //Aquí ponemos el nombre y la contraseña
        String user = "pedro";
        String pass = "abcd";

        //Los pasamos al CallbackHandler para que el LoginModule acceda a ellos
        CallbackHandler handler = new MyCallbackHandler(user, pass);

        try {
            //Creamos el LoginContext
            EjemploLoginModule loginContext = new EjemploLoginModule();

            //Llamamos al método login para realizar la autenticación
            loginContext.login();
            System.out.println("Usuario autenticado...");
        } catch (LoginException e) {
            // Si la autenticación no tiene éxito
            System.err.println("ERROR -> No se puede autenticar el usuario");
        }
    }
}
