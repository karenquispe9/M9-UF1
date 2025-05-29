package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    private static final String ALGORISME_XIFRAT = "AES";
    private static final String ALGORISME_HASH = "SHA-256";
    private static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] iv = new byte[MIDA_IV];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(clau), new IvParameterSpec(iv));

            byte[] encrypted = cipher.doFinal(msg.getBytes());
            byte[] combined = new byte[iv.length + encrypted.length];

            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

            return new TextXifrat(combined);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error en xifrat AES");
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] combined = xifrat.getBytes();
            byte[] iv = new byte[MIDA_IV];
            System.arraycopy(combined, 0, iv, 0, iv.length);

            byte[] encrypted = new byte[combined.length - MIDA_IV];
            System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(clau), new IvParameterSpec(iv));
            byte[] decrypted = cipher.doFinal(encrypted);

            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error en desxifrat AES");
        }
    }

    /**
     * Genera una clau secreta a partir de la contrasenya mitjançant SHA-256.
     */

    private SecretKeySpec generateKey(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = digest.digest(password.getBytes());
        byte[] key = new byte[32]; // 256 bits
        System.arraycopy(hash, 0, key, 0, key.length);
        return new SecretKeySpec(key, ALGORISME_XIFRAT);
    }
}