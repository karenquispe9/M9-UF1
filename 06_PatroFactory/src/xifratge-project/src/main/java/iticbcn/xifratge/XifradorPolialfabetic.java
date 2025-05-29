package iticbcn.xifratge;

import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZàáèéíïòóúüçñÀÁÈÉÍÏÒÓÚÜÇÑ";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException ex) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        Random random = new Random(seed);
        StringBuilder resultado = new StringBuilder();

        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                char[] perm = permutaAlfabet(random);
                int idx = ALFABETO.indexOf(c);
                resultado.append(perm[idx]);
            } else {
                resultado.append(c);
            }
        }

        return new TextXifrat(resultado.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException ex) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        Random random = new Random(seed);
        StringBuilder resultado = new StringBuilder();
        String msg = new String(xifrat.getBytes());

        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                char[] perm = permutaAlfabet(random);
                int idx = indexOf(perm, c);
                resultado.append(ALFABETO.charAt(idx));
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    private char[] permutaAlfabet(Random r) {
        char[] abc = ALFABETO.toCharArray();
        for (int i = abc.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            char temp = abc[i];
            abc[i] = abc[j];
            abc[j] = temp;
        }
        return abc;
    }

    private int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) return i;
        }
        return -1;
    }
}