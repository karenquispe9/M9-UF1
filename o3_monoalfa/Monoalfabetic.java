package o3_monoalfa;

import java.util.*;

public class Monoalfabetic {

    public static final char[] ALFABET_MAYUSCULES = "AÁÀÂÄBCÇDEÉÈÊËFGHIÍÏJKLMNÑOÓÒÖÔPQRSTUÚÜÙVWXYÝZ".toCharArray();
    public static char[] alfabetPermutat;  

    //  genera una permutació de l'alfabet
    public static char[] permutaAlfabet(char[] alfabet) {
        //array de caracteres -> lista de objetos Character
        List<Character> llistaAlfabet = new ArrayList<>();
        for (char c : alfabet) {
            llistaAlfabet.add(c);
        }

        Collections.shuffle(llistaAlfabet);  

        // llista torna a  un array primitiu de char
        alfabetPermutat = new char[alfabet.length];
        for (int i = 0; i < llistaAlfabet.size(); i++) {
            alfabetPermutat[i] = llistaAlfabet.get(i);
        }
        return alfabetPermutat;
    }

    // metode per cifrar una cadena utilitzant l'alfabet permutaat
    public static String xifraMonoAlfa(String cadena) {
        StringBuilder textXifrat = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            boolean isUpper = Character.isUpperCase(c);
            char originalChar = Character.toUpperCase(c);  // pasem a mayuscules per simplificar

            // busca la posicio de la lletra en l'alfabet original
            int index = new String(ALFABET_MAYUSCULES).indexOf(originalChar);
            if (index != -1) {
                //substitució per al caracter corresponent
                char charXifrat = alfabetPermutat[index];
                textXifrat.append(isUpper ? charXifrat : Character.toLowerCase(charXifrat));
            } else {
                //si no es una lletra deixa el caracter tal cual
                textXifrat.append(c);
            }
        }
        return textXifrat.toString();
    }

    //metode per descifrar
    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder textDesxifrat = new StringBuilder();
        for (char c : cadena.toCharArray()) {
            boolean isUpper = Character.isUpperCase(c);
            char encryptedChar = Character.toUpperCase(c);  

            //busquem la posicio de la lletra
            int index = new String(alfabetPermutat).indexOf(encryptedChar);
            if (index != -1) {
                //substituim per al caracter de l'abecedari original
                char charOriginal = ALFABET_MAYUSCULES[index];
                textDesxifrat.append(isUpper ? charOriginal : Character.toLowerCase(charOriginal));
            } else {

                textDesxifrat.append(c);
            }
        }
        return textDesxifrat.toString();
    }


    public static void main(String[] args) {
    
        permutaAlfabet(ALFABET_MAYUSCULES);

        
        System.out.println("Alfabet original: " + new String(ALFABET_MAYUSCULES));
        System.out.println("Alfabet permutat: " + new String(alfabetPermutat));

        
        String text = "Hola! Aquesta es una prova del Xifrat Monoalfa. Adeu.";
        System.out.println("Text original: " + text);

     
        String textXifrat = xifraMonoAlfa(text);
        System.out.println("Text xifrat: " + textXifrat);

        String textDesxifrat = desxifraMonoAlfa(textXifrat);
        System.out.println("Text desxifrat: " + textDesxifrat);
    }
}
