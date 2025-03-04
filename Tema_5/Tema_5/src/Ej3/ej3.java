package Ej3;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class ej3 {
    public static void main(String[] args) {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            SecureRandom numero = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(2048, numero);

            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavePrivada = par.getPrivate();
            PublicKey clavePublica = par.getPublic();

            PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
            FileOutputStream outPriv = new FileOutputStream("Clave.privada");
            outPriv.write(pkcs8Spec.getEncoded());
            outPriv.close();

            X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(clavePublica.getEncoded());
            FileOutputStream outPub = new FileOutputStream("Clave.publica");
            outPub.write(x509Spec.getEncoded());
            outPub.close();

            System.out.println("Claves guardadas correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

