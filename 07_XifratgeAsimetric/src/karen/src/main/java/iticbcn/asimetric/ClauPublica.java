package iticbcn.asimetric;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;


public class ClauPublica {

    /**
     * Genera un parell de claus RSA (pública i privada)
     */
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048); // Tamaño de clave segura

        return kpg.generateKeyPair();
    }

    /**
     * Xifra un missatge amb una clau pública RSA
     */
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // Opció PKCS1 o OAEP
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        return cipher.doFinal(msg.getBytes());
    }

    /**
     * Desxifra un missatge amb una clau privada RSA
     */
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] decryptedBytes = cipher.doFinal(msgXifrat);

        return new String(decryptedBytes);
    }
}
