package Ej5;

import java.io.IOException;
import java.util.Map;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private boolean autenticado = false;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        // Crear array de Callbacks
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            // Invocar al método handle de MyCallbackHandler
            callbackHandler.handle(callbacks);

            String usuario = ((NameCallback) callbacks[0]).getName();
            char[] pass = ((PasswordCallback) callbacks[1]).getPassword();
            String clave = new String(pass);

            // Autenticación del usuario "pedro" con clave "abcd"
            if ("pedro".equals(usuario) && "abcd".equals(clave)) {
                autenticado = true;
                return true;
            } else {
                throw new LoginException("Usuario o clave incorrectos");
            }
        } catch (IOException | UnsupportedCallbackException e) {
            throw new LoginException("Error al procesar las credenciales");
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return autenticado;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
