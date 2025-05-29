public class Rot13 {

    private static final char[] MINUSCULAS = "abcdefghijklmnopqrstuvwxyzàáèéíïòóúüç".toCharArray();
    private static final char[] MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÈÉÍÏÒÓÚÜÇ".toCharArray();

    
    public static String xifraRot13(String cadena) {
        StringBuilder resultado = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultado.append(rotarLetra(c, MINUSCULAS, 13));
            } else if (Character.isUpperCase(c)) {
                resultado.append(rotarLetra(c, MAYUSCULAS, 13));
            } else {
                resultado.append(c); // Dejamos sin cambiar espacios y signos
            }
        }

        return resultado.toString();
    }

    
    public static String desxifraRot13(String cadena) {
        StringBuilder resultado = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            if (Character.isLowerCase(c)) {
                resultado.append(rotarLetra(c, MINUSCULAS, -13));
            } else if (Character.isUpperCase(c)) {
                resultado.append(rotarLetra(c, MAYUSCULAS, -13));
            } else {
                resultado.append(c); // Dejamos sin cambiar espacios y signos
            }
        }

        return resultado.toString();
    }


    // Método auxiliar para rotar una letra dentro del array correspondiente
    private static char rotarLetra(char c, char[] abecedario, int desplazamiento) {
        for (int i = 0; i < abecedario.length; i++) {
            if (c == abecedario[i]) {
                int nuevoIndice = (i + desplazamiento + abecedario.length) % abecedario.length;
                return abecedario[nuevoIndice];
            }
        }
        return c; // Si no se encuentra, devolvemos el mismo carácter
    }

  
    
    public static void main(String[] args) {
        String textoOriginal = "Fent el que no he fet quan tocaba";
        String textoCifrado = xifraRot13(textoOriginal);
        String textoDescifrado = desxifraRot13(textoCifrado);

        System.out.println("Original:     " + textoOriginal);
        System.out.println("Cifrado:      " + textoCifrado);
        System.out.println("Descifrado:   " + textoDescifrado);
    }
}