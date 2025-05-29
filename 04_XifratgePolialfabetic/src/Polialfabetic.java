import java.util.Random;

public class Polialfabetic {
    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZàáèéíïòóúüçñÀÁÈÉÍÏÒÓÚÜÇÑ";
    
    private static Random random;

    // inicializar la semilla aleatoria
    public static void initRandom(String claveSecreta) {
        random = new Random(claveSecreta.hashCode());
    }

    // genera una permutación aleatoria del alfabeto
    public static char[] permutaAlfabet() {
        char[] alfabeto = ALFABETO.toCharArray();
        for (int i = alfabeto.length - 1; i > 0; i--) {
            int indiceAleatorio = random.nextInt(i + 1);
            // Intercambiar elementos
            char temp = alfabeto[i];
            alfabeto[i] = alfabeto[indiceAleatorio];
            alfabeto[indiceAleatorio] = temp;
        }
        return alfabeto;
    }

    // ciframos el mensaje
    public static String xifraPoliAlfa(String msg) {
        StringBuilder resultado = new StringBuilder();
        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) { // Si la letra está en el alfabeto
                char[] permutacion = permutaAlfabet();
                int indiceOriginal = ALFABETO.indexOf(c);
                char letraXifrada = permutacion[indiceOriginal];
                resultado.append(letraXifrada);
            } else {
                resultado.append(c); // Dejar sin cambiar si no está en el alfabeto
            }
        }
        return resultado.toString();
    }

    //  desciframos el mensaje
    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultado = new StringBuilder();
        for (char c : msgXifrat.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) { 
                char[] permutacion = permutaAlfabet();
                int indicePermutado = indexOf(permutacion, c);
                char letraDesxifrada = ALFABETO.charAt(indicePermutado);
                resultado.append(letraDesxifrada);
            } else {
                resultado.append(c); 
            }
        }
        return resultado.toString();
    }

    // Método auxiliar para buscar el índice de un carácter en un array
    private static int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1; // No encontrado
    }

    public static void main(String[] args) {
        String[] msgs = {
            "Test 01 àrbitre, coixí, Perimètre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String[] msgsXifrats = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom("claveSecreta"); // Semilla fija para reproducibilidad
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom("claveSecreta"); // Semilla fija para reproducibilidad
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}