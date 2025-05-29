package iticbcn.xifratge;


public class XifradorMonoalfabetic implements Xifrador {

    private static final String ALFABETO = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZàáèéíïòóúüçñÀÁÈÉÍÏÒÓÚÜÇÑ";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null)
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

        StringBuilder resultado = new StringBuilder();
        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                int index = ALFABETO.indexOf(c);
                char letraCifrada = ALFABETO.charAt((index + 13) % ALFABETO.length());
                resultado.append(letraCifrada);
            } else {
                resultado.append(c);
            }
        }
        return new TextXifrat(resultado.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null)
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");

        String msg = new String(xifrat.getBytes());
        StringBuilder resultado = new StringBuilder();

        for (char c : msg.toCharArray()) {
            if (ALFABETO.indexOf(c) != -1) {
                int index = ALFABETO.indexOf(c);
                char letraOriginal = ALFABETO.charAt((index + ALFABETO.length() - 13) % ALFABETO.length());
                resultado.append(letraOriginal);
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}