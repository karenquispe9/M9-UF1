public class RotX {
    private static final char[] MINUSCULAS = "abcdefghijklmnñopqrstuvwxyzàáèéíïòóúüç".toCharArray();
    private static final char[] MAYUSCULAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZÀÁÈÉÍÏÒÓÚÜÇ".toCharArray();

    //  cifrar con desplazamiento personalizado
    public static String xifraRotX(String cadena, int desplacament) {
        return rotarCadena(cadena, desplacament);
    }

    // descifrar con desplazamiento personalizado
    public static String desxifraRotX(String cadena, int desplacament) {
        return rotarCadena(cadena, -desplacament);
    }

    private static String rotarCadena(String cadena, int desplazament) {
        StringBuilder resultado = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultado.append(rotarLetra(c, MINUSCULAS, desplazament));
            } else if (Character.isUpperCase(c)) {
                resultado.append(rotarLetra(c, MAYUSCULAS, desplazament));
            } else {
                resultado.append(c); 
            }
        }

        return resultado.toString();
    }

    // rotar una letra dentro del array correspondiente
    private static char rotarLetra(char c, char[] abecedario, int desplazament) {
        for (int i = 0; i < abecedario.length; i++) {
            if (c == abecedario[i]) {
                int nuevoIndice = (i + desplazament + abecedario.length) % abecedario.length;
                return abecedario[nuevoIndice];
            }
        }
        return c; // Si no se encuentra, devolvemos el mismo carácter
    }

    // fuerza bruta para probar todos los desplazamientos posibles
    public static void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("\nDescifrando por força bruta:");
        for (int i = 1; i < MINUSCULAS.length; i++) {
            String intento = desxifraRotX(cadenaXifrada, i);
            System.out.printf("Desplaçament %2d: %s\n", i, intento);
        }
    }

    
    public static void main(String[] args) {
        String textoOriginal = "Vull aprovar aquesta assignatura.";
        int desplazament = 5;

        String textoCifrado = xifraRotX(textoOriginal, desplazament);
        String textoDescifrado = desxifraRotX(textoCifrado, desplazament);

        System.out.println("Original:     " + textoOriginal);
        System.out.println("Cifrado (+" + desplazament + "): " + textoCifrado);
        System.out.println("Descifrado:   " + textoDescifrado);

        // Fuerza bruta
        forcaBrutaRotX(textoCifrado);
    }
}