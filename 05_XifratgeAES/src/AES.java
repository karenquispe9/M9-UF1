import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16; // Tamaño del IV para AES (16 bytes)
    private static byte[] iv = new byte[MIDA_IV];

    // Clave base (para probar, aunque también se puede pasar por parámetro)
    private static final String CLAU = "78d9uTlZKzJqjBp1mQnWvRfEaY3sLcT7wN+O/LkXhPzgI0U=";

    /**
     * Cifra un mensaje usando AES-256-CBC
     */
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Generamos un IV aleatori
        SecureRandom random = new SecureRandom();
        iv = new byte[MIDA_IV];
        random.nextBytes(iv);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] msgBytes = msg.getBytes();

        // Generamos clave secreta a partir del password (clau)"
        byte[] key = hashPassword(clau);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORISME_XIFRAT);

        // Configurar Cipher
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Cifrar
        byte[] encrypted = cipher.doFinal(msgBytes);

        // Combinar IV + mensaje cifrado
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);

        return ivAndEncrypted;
    }

    /**
     * Descifra un mensaje cifrado con AES-256-CBC
     */
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extraer el IV (primeros 16 bytes)
        byte[] extractedIV = new byte[MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, 0, extractedIV, 0, MIDA_IV);

        IvParameterSpec ivSpec = new IvParameterSpec(extractedIV);

        // Extraer el mensaje cifrado (resto del array)
        byte[] encryptedMsg = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, encryptedMsg, 0, encryptedMsg.length);

        // Generar clave secreta a partir de la contraseña
        byte[] key = hashPassword(clau);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORISME_XIFRAT);

        // Configurar Cipher
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Descifrar
        byte[] decryptedBytes = cipher.doFinal(encryptedMsg);
        return new String(decryptedBytes);
    }

    /**
     * Método auxiliar: genera una clave de 256 bits (32 bytes) a partir de una contraseña
     */
    private static byte[] hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(password.getBytes());
        return Arrays.copyOf(hash, 32); // 256 bits = 32 bytes
    }

    
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
                    String msg = msgs[i];
                    byte[] bXifrats = null;
                    String desxifrat = "";
                    try {
                        bXifrats = xifraAES(msg, CLAU);
                        desxifrat = desxifraAES (bXifrats, CLAU);
        
        
                    } catch (Exception e) {
                    System.err.println("Error de xifrat: "
                    + e.getLocalizedMessage ());
        
        }
        System.out.println("--------------------" );
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
        }
    }
}