package Ej5;

import java.io.*;
import javax.security.auth.callback.*;

public class MyCallbackHandler implements CallbackHandler {
    private String usuario;
    private String clave;

    // Constructor recibe parámetros usuario y clave
    public MyCallbackHandler(String usu, String clave) {
        this.usuario = usu;
        this.clave = clave;
    }

    // Método handle será invocado por el LoginModule
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            Callback callback = callbacks[i];
            if (callback instanceof NameCallback) {
                NameCallback nameCB = (NameCallback) callback;
                nameCB.setName("pedro"); // Cambiado a pedro
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCB = (PasswordCallback) callback;
                passwordCB.setPassword("abcd".toCharArray()); // Cambiado a abcd
            }
        }
    }
}

