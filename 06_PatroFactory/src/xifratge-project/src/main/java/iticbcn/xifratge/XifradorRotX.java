package iticbcn.xifratge;



public class XifradorRotX implements Xifrador {

    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZàáèéíïòóúüçñÀÁÈÉÍÏÒÓÚÜÇÑ";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplaçament = parseDesplacament(clau);
        StringBuilder resultado = new StringBuilder();

        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                int idx = ALFABETO.indexOf(c);
                char cXifrat = ALFABETO.charAt((idx + desplaçament) % ALFABETO.length());
                resultado.append(cXifrat);
            } else {
                resultado.append(c);
            }
        }

        return new TextXifrat(resultado.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplaçament = parseDesplacament(clau);
        StringBuilder resultado = new StringBuilder();
        String msg = new String(xifrat.getBytes());

        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                int idx = ALFABETO.indexOf(c);
                char cOriginal = ALFABETO.charAt((idx + ALFABETO.length() - desplaçament) % ALFABETO.length());
                resultado.append(cOriginal);
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    private int parseDesplacament(String clau) throws ClauNoSuportada {
        try {
            int n = Integer.parseInt(clau);
            if (n < 0 || n > 40) throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            return n;
        } catch (NumberFormatException ex) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
}